package br.com.unimedceara.core.config;

import br.com.unimedceara.core.services.api.ComputerIdBridgeClient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfiguration {

	@Bean
	public ComputerIdBridgeClient apiClient() {
		OkHttpClient httpClient = new OkHttpClient.Builder()
				                          .addInterceptor(chain -> {
					                          Request original = chain.request();
					                          Request.Builder requestBuilder = original.newBuilder()
							                                                           .method(original.method(), original.body())
							                                                           .addHeader("Content-Type", "application/json")
							                                                           .addHeader("Accept", "*/*")
							                                                           .addHeader("Authorization", "Bearer " +
									                                                                                       "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9." +
									                                                                                       "eyJpZF9jbGllbnRlIjo2OSwiaWRfY2xpZW50ZV90" +
									                                                                                       "YWdlZCI6MTEwLCJlbWFpbCI6ImluYWNpb2R1dHJhQH" +
									                                                                                       "VuaW1lZGNlYXJhLmNvbS5iciIsInRhZ3MiOlsiVHJp" +
									                                                                                       "YWwiXX0.Yuiaqx0DdxjRxu9uFtwGRudAE5zFmPS741EyEbOTmPk");
											  //TODO: nao usar Bearer como está. e url
					                          Request request = requestBuilder.build();
					                          return chain.proceed(request);
				                          })
				                          .build();

		Retrofit retrofit = new Retrofit.Builder()
				                    .baseUrl("https://gateway.apifacialid.com.br/")
				                    .addConverterFactory(GsonConverterFactory.create())
				                    .client(httpClient)
				                    .build();

		return retrofit.create(ComputerIdBridgeClient.class);
	}

}
