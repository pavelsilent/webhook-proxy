package pro.sisit.utils.webhookproxy.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.MergeRequestModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.ProjectModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.data.UserModel;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.MergeRequestEvent;
import pro.sisit.utils.webhookproxy.domain.model.gitlab.event.PushEvent;

import java.util.stream.Stream;

public class ObjectUtilTest {
    @ParameterizedTest
    @ArgumentsSource(GetPropertyValueArguments.class)
    public void getPropertyValue(Object object, String fieldPath, Object expectedValue) {
        Assertions.assertEquals(expectedValue, ObjectUtil.getFieldValue(object, fieldPath));
    }

    static class GetPropertyValueArguments implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    new Object[]{
                            PushEvent.builder().user(UserModel.builder().name("Pavel").build()).build(),
                            "user.name", "Pavel"},
                    new Object[]{
                            MergeRequestEvent.builder().mergeRequest(
                                    MergeRequestModel.builder()
                                            .source(ProjectModel.builder().name("jetalon/data-types").build())
                                            .build()).build(), "mergeRequest.source.name", "jetalon/data-types"})
                    .map(Arguments::of);
        }
    }

}