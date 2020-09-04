package pro.sisit.utils.webhookproxy.rest.dto.gitlab.hook.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeDiffDTO {

    @JsonProperty("diff")
    public String diff;
    @JsonProperty("new_path")
    public String newPath;
    @JsonProperty("old_path")
    public String oldPath;
    @JsonProperty("a_mode")
    public String aMode;
    @JsonProperty("b_mode")
    public String bMode;
    @JsonProperty("new_file")
    public Boolean newFile;
    @JsonProperty("renamed_file")
    public Boolean renamedFile;
    @JsonProperty("deleted_file")
    public Boolean deletedFile;
}
