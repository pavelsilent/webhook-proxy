package pro.sisit.utils.webhookproxy.service.transform;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.sisit.utils.webhookproxy.domain.enumeration.FilterTypeEnum;
import pro.sisit.utils.webhookproxy.domain.enumeration.TargetTypeEnum;
import pro.sisit.utils.webhookproxy.domain.model.rule.FilterModel;
import pro.sisit.utils.webhookproxy.domain.model.rule.ProxyRuleInitialModel;
import pro.sisit.utils.webhookproxy.domain.model.rule.TargetModel;
import pro.sisit.utils.webhookproxy.domain.model.rule.filter.ClassNameFilterModel;
import pro.sisit.utils.webhookproxy.domain.model.rule.filter.FieldValueFilterModel;
import pro.sisit.utils.webhookproxy.domain.model.rule.filter.SystemFilterModel;
import pro.sisit.utils.webhookproxy.domain.model.rule.target.TelegramChannelTargetModel;
import pro.sisit.utils.webhookproxy.rest.dto.rule.FilterDTO;
import pro.sisit.utils.webhookproxy.rest.dto.rule.ProxyRuleInitialDTO;
import pro.sisit.utils.webhookproxy.rest.dto.rule.TargetDTO;
import pro.sisit.utils.webhookproxy.rest.dto.rule.filter.ClassNameFilterDTO;
import pro.sisit.utils.webhookproxy.rest.dto.rule.filter.FieldValueFilterDTO;
import pro.sisit.utils.webhookproxy.rest.dto.rule.filter.SystemFilterDTO;
import pro.sisit.utils.webhookproxy.rest.dto.rule.target.TelegramChannelTargetDTO;

@Service
@RequiredArgsConstructor
public class RuleRestConverterImpl implements RuleRestConverter {

    @Override
    public ProxyRuleInitialModel toModel(ProxyRuleInitialDTO dto) {
        if (dto == null) {
            return null;
        }

        ProxyRuleInitialModel model = new ProxyRuleInitialModel();
        model.setSource(dto.source);
        model.setTarget(toModel(dto.target));
        model.setFilters(
            Optional.ofNullable(dto.filters)
                    .map(list -> list.stream()
                                     .map(this::toModel)
                                     .filter(Objects::nonNull)
                                     .collect(Collectors.toList()))
                    .orElseGet(ArrayList::new));

        return model;
    }

    public TargetModel toModel(TargetDTO dto) {
        if (dto == null) {
            return null;
        }

        if (dto instanceof TelegramChannelTargetDTO) {
            TelegramChannelTargetDTO targetDTO = (TelegramChannelTargetDTO) dto;

            TelegramChannelTargetModel model = new TelegramChannelTargetModel();
            model.setTarget(TargetTypeEnum.TELEGRAM_CHANNEL);
            model.setChannelId(targetDTO.channelId);
            model.setBotToken(targetDTO.botToken);

            return model;
        }
        throw new RuntimeException("Unsupported target dto class " + dto.getClass().getSimpleName());
    }

    public FilterModel toModel(FilterDTO dto) {
        if (dto == null) {
            return null;
        }

        if (dto instanceof SystemFilterDTO) {
            SystemFilterDTO filterDTO = (SystemFilterDTO) dto;

            SystemFilterModel model = new SystemFilterModel();
            model.setType(FilterTypeEnum.SYSTEM);
            model.setValue(filterDTO.value);
            return model;
        } else if (dto instanceof ClassNameFilterDTO) {
            ClassNameFilterDTO filterDTO = (ClassNameFilterDTO) dto;

            ClassNameFilterModel model = new ClassNameFilterModel();
            model.setType(FilterTypeEnum.CLASS_NAME);
            model.setClassName(filterDTO.className);
            return model;

        } else if (dto instanceof FieldValueFilterDTO) {
            FieldValueFilterDTO filterDTO = (FieldValueFilterDTO) dto;

            FieldValueFilterModel model = new FieldValueFilterModel();
            model.setType(FilterTypeEnum.CLASS_NAME);
            model.setClassName(filterDTO.className);
            model.setPropertyPath(filterDTO.propertyPath);
            model.setPropertyValue(filterDTO.propertyValue);
            return model;
        }
        throw new RuntimeException("Unsupported filter dto class " + dto.getClass().getSimpleName());
    }

}
