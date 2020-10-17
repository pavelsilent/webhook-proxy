package pro.sisit.utils.webhookproxy.service.rule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.data.TelegramBotRepository;
import pro.sisit.utils.webhookproxy.data.TelegramChannelRepository;
import pro.sisit.utils.webhookproxy.domain.entity.Target;
import pro.sisit.utils.webhookproxy.domain.entity.TelegramBot;
import pro.sisit.utils.webhookproxy.domain.entity.target.TelegramChannel;
import pro.sisit.utils.webhookproxy.domain.model.rule.target.TelegramChannelTargetModel;

@Service
@RequiredArgsConstructor
public class TelegramChannelService implements TargetService<TelegramChannelTargetModel> {

    private final TelegramBotRepository botRepository;
    private final TelegramChannelRepository repository;

    @Override
    public Target ensure(TelegramChannelTargetModel model) {
        TelegramBot bot = botRepository.findFirstByToken(model.getBotToken())
                                       .orElseGet(() -> {
                                           TelegramBot newBot = new TelegramBot();
                                           newBot.setToken(model.getBotToken());
                                           return botRepository.save(newBot);
                                       });

        return repository.findFirstByBotAndChannelId(bot, model.getChannelId())
                         .orElseGet(() -> {
                             TelegramChannel channel = new TelegramChannel();
                             channel.setBot(bot);
                             channel.setChannelId(model.getChannelId());
                             return repository.save(channel);
                         });
    }

    @Override
    public boolean supports(Class modelClass) {
        return TelegramChannelTargetModel.class.equals(modelClass);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
        botRepository.deleteAll();
    }
}
