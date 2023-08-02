//package br.com.unimedceara.core.services.api;
//
//
//import br.com.unimedceara.core.model.api.DataApi;
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.http.*;
//
//public interface ComputerIdBridgeClient {
//	@POST("wsfaceid/v2/verify")
//	@FormUrlEncoded
//	Call<ResponseBody> verifyFace(
//			@Field("fot64") String fot64,
//			@Field("idPessoa") String id,
//			@Field("data") DataApi data
//	);
//	@GET("wsfacecid/v2/exists/{idPessoa}")
//	Call<ResponseBody> exists(
//			@Path("idPessoa") String id);
//	@POST("wsfacecid/v2/register")
//	@FormUrlEncoded
//	Call<ResponseBody> createBiometry(
//			@Field("fotob64") String fot64,
//			@Field("idPessoa") String id,
//			@Field("data") DataApi data);
//
//	@GET("wsfacecid/v2/register")
//	@FormUrlEncoded
//	Call<ResponseBody> updateBiometry(
//			@Field("fotob64") String fot64,
//			@Field("idPessoa") String id,
//			@Field("data") DataApi data);
//
//	@DELETE("wsfacecid/v2/deactivate/{idPessoa}")
//	Call<ResponseBody> deleteBiometry(
//			@Path("idPessoa") String idPessoa);
//
//}
