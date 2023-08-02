package br.com.unimedceara.core.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DataApi2 {

	@JsonProperty("additionalProp1")
	private String additionalProp1 ="";

	@JsonProperty("additionalProp2")
	private String additionalProp2="";

	@JsonProperty("additionalProp3")
	private String additionalProp3="";
}
