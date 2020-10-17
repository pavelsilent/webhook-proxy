package pro.sisit.utils.webhookproxy.usecase;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pro.sisit.utils.webhookproxy.WebHookProxyTestApplication;
import pro.sisit.utils.webhookproxy.data.ClassNameFilterRepository;
import pro.sisit.utils.webhookproxy.data.FieldValueFilterRepository;
import pro.sisit.utils.webhookproxy.data.ProxyRuleFilterRepository;
import pro.sisit.utils.webhookproxy.data.SystemFilterRepository;
import pro.sisit.utils.webhookproxy.data.TelegramBotRepository;
import pro.sisit.utils.webhookproxy.data.TelegramChannelRepository;
import pro.sisit.utils.webhookproxy.domain.entity.ProxyRuleFilter;
import pro.sisit.utils.webhookproxy.domain.entity.TelegramBot;
import pro.sisit.utils.webhookproxy.domain.entity.target.TelegramChannel;
import pro.sisit.utils.webhookproxy.domain.model.rule.ProxyRuleInitialModel;
import pro.sisit.utils.webhookproxy.rest.dto.rule.ProxyRuleInitialDTO;
import pro.sisit.utils.webhookproxy.rest.dto.rule.target.TelegramChannelTargetDTO;
import pro.sisit.utils.webhookproxy.service.cqs.CommandHandlerFactory;
import pro.sisit.utils.webhookproxy.service.transform.RuleRestConverter;

@SpringBootTest(classes = WebHookProxyTestApplication.class)
public class ProxyRuleCreateHandlerTest {

    @Autowired
    private RuleRestConverter ruleRestConverter;

    @Autowired
    private CommandHandlerFactory commandDispatcher;

    @Autowired
    private TelegramBotRepository botRepository;

    @Autowired
    private TelegramChannelRepository telegramChannelRepository;

    @Autowired
    private SystemFilterRepository systemFilterRepository;

    @Autowired
    private ClassNameFilterRepository classNameFilterRepository;

    @Autowired
    private FieldValueFilterRepository fieldValueFilterRepository;

    @Autowired
    private ProxyRuleFilterRepository proxyRuleFilterRepository;

    @Test
    public void testCreateProxyRule() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(
            classLoader.getResource("rule/proxy-rule-initial-dto.json")).getFile());

        ObjectMapper mapper = new ObjectMapper();
        ProxyRuleInitialDTO dto = mapper.readValue(file, ProxyRuleInitialDTO.class);
        ProxyRuleInitialModel model = ruleRestConverter.toModel(dto);

        ProxyRuleCreateCommand command = new ProxyRuleCreateCommand();
        command.setModel(model);

        Long newProxyRuleId = commandDispatcher.process(command);
        Assertions.assertNotNull(newProxyRuleId);

        TelegramChannelTargetDTO channelTargetDTO = (TelegramChannelTargetDTO) dto.target;

        TelegramBot bot = botRepository.findFirstByToken(channelTargetDTO.botToken).orElse(null);
        Assertions.assertNotNull(bot);

        TelegramChannel channel = telegramChannelRepository.findFirstByBotAndChannelId(bot, channelTargetDTO.channelId)
                                                           .orElse(null);
        Assertions.assertNotNull(channel);

        List<ProxyRuleFilter> filters = proxyRuleFilterRepository.findAllByRuleId(newProxyRuleId);
        Assertions.assertEquals(6, filters.size());
    }
}
