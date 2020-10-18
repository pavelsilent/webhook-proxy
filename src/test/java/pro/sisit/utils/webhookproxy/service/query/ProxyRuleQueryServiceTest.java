package pro.sisit.utils.webhookproxy.service.query;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import pro.sisit.utils.webhookproxy.WebHookProxyTestApplication;
import pro.sisit.utils.webhookproxy.data.*;
import pro.sisit.utils.webhookproxy.domain.Event;
import pro.sisit.utils.webhookproxy.domain.Source;
import pro.sisit.utils.webhookproxy.domain.entity.ProxyRule;
import pro.sisit.utils.webhookproxy.domain.entity.ProxyRuleFilter;
import pro.sisit.utils.webhookproxy.domain.entity.TelegramBot;
import pro.sisit.utils.webhookproxy.domain.entity.filter.ClassNameFilter;
import pro.sisit.utils.webhookproxy.domain.entity.filter.FieldValueFilter;
import pro.sisit.utils.webhookproxy.domain.entity.filter.SystemFilter;
import pro.sisit.utils.webhookproxy.domain.entity.target.TelegramChannel;
import pro.sisit.utils.webhookproxy.domain.enumeration.SystemFilterEnum;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.MergeRequestModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.ProjectModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.*;
import pro.sisit.utils.webhookproxy.domain.model.jenkins.JenkinsEvent;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest(classes = WebHookProxyTestApplication.class)
@Transactional
public class ProxyRuleQueryServiceTest {

    @Autowired
    private TelegramBotRepository telegramBotRepository;

    @Autowired
    private TelegramChannelRepository telegramChannelRepository;

    @Autowired
    private ProxyRuleRepository proxyRuleRepository;

    @Autowired
    private ProxyRuleFilterRepository proxyRuleFilterRepository;

    @Autowired
    private SystemFilterRepository systemFilterRepository;

    @Autowired
    private ClassNameFilterRepository classNameFilterRepository;

    @Autowired
    private FieldValueFilterRepository fieldValueFilterRepository;

    @Autowired
    private ProxyRuleQueryService proxyRuleQueryService;

    @Autowired
    private Environment environment;

    @BeforeEach
    public void setUp() {
        TelegramBot telegramBot = new TelegramBot();
        telegramBot.setToken(environment.getProperty("telegram.bot.id"));
        telegramBotRepository.save(telegramBot);

        TelegramChannel telegramChannel = new TelegramChannel();
        telegramChannel.setBot(telegramBot);
        telegramChannel.setChannelId(environment.getProperty("telegram.channel.id"));
        telegramChannelRepository.save(telegramChannel);

        ProxyRule proxyRule = new ProxyRule();
        proxyRule.setSource(Source.GITLAB);
        proxyRule.setTarget(telegramChannel);
        proxyRuleRepository.save(proxyRule);

        SystemFilter filter = new SystemFilter();
        filter.setType(SystemFilterEnum.ALL);
        systemFilterRepository.save(filter);

        ProxyRuleFilter proxyRuleFilter = new ProxyRuleFilter();
        proxyRuleFilter.setFilter(filter);
        proxyRuleFilter.setRule(proxyRule);
        proxyRuleFilterRepository.save(proxyRuleFilter);

        ProxyRule proxyRuleByClassName = new ProxyRule();
        proxyRuleByClassName.setSource(Source.GITLAB);
        proxyRuleByClassName.setTarget(telegramChannel);
        proxyRuleRepository.save(proxyRuleByClassName);

        List<Class> events = Arrays.asList(PushEvent.class, MergeRequestEvent.class, CommitCommentEvent.class);
        for (Class eventClass : events) {
            ClassNameFilter classNameFilter = new ClassNameFilter();
            classNameFilter.setClassName(eventClass.getCanonicalName());
            classNameFilterRepository.save(classNameFilter);

            ProxyRuleFilter proxyRuleClassNameFilter = new ProxyRuleFilter();
            proxyRuleClassNameFilter.setFilter(classNameFilter);
            proxyRuleClassNameFilter.setRule(proxyRuleByClassName);
            proxyRuleFilterRepository.save(proxyRuleClassNameFilter);
        }

        ///
        ProxyRule proxyRuleByFieldValue = new ProxyRule();
        proxyRuleByFieldValue.setSource(Source.GITLAB);
        proxyRuleByFieldValue.setTarget(telegramChannel);
        proxyRuleRepository.save(proxyRuleByFieldValue);

        FieldValueFilter fieldValueFilter = new FieldValueFilter();
        fieldValueFilter.setClassName(MergeRequestEvent.class.getCanonicalName());
        fieldValueFilter.setPropertyPath("mergeRequest.source.name");
        fieldValueFilter.setPropertyValue("etalon/data-types");
        fieldValueFilterRepository.save(fieldValueFilter);

        ProxyRuleFilter proxyRuleFieldValueFilter = new ProxyRuleFilter();
        proxyRuleFieldValueFilter.setFilter(fieldValueFilter);
        proxyRuleFieldValueFilter.setRule(proxyRuleByClassName);
        proxyRuleFilterRepository.save(proxyRuleFieldValueFilter);

    }

    @ParameterizedTest
    @ArgumentsSource(ProxyRuleQueryServiceTest.FindTargetArguments.class)
    public void findTargetsTest(Event event, Integer expectedTargets) {
        proxyRuleQueryService.findTargets(event).size();
        Assertions.assertEquals(expectedTargets, proxyRuleQueryService.findTargets(event).size());
    }

    static class FindTargetArguments implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    new Object[]{new PushEvent(), 2},
                    new Object[]{new MergeRequestEvent(), 2},
                    new Object[]{new PipelineEvent(), 1},
                    new Object[]{new JenkinsEvent(), 0},
                    new Object[]{MergeRequestCommentEvent.builder()
                            .mergeRequest(MergeRequestModel.builder()
                                    .source(ProjectModel.builder().name("etalon/data-types").build()).build()).build(), 1},
                    new Object[]{MergeRequestCommentEvent.builder()
                            .mergeRequest(MergeRequestModel.builder()
                                    .source(ProjectModel.builder().name("jetalon/data-types").build()).build()).build(), 1}
                                    // 1 - т.к. есть общий
            )
                    .map(Arguments::of);
        }
    }
}
