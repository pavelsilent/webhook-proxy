package pro.sisit.utils.webhookproxy.service.transform;

public interface RestConverter<T, K> {

    T toModel(K dto);

    boolean supports(Object dto);
}
