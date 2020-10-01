package pro.sisit.utils.webhookproxy.service.transform.event;

public interface GitlabEventRestConverter<T, K> {

    T toModel(K dto);

    boolean supports(Object dto);
}
