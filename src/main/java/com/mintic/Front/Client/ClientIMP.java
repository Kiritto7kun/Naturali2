package com.mintic.Front.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import com.mintic.Front.Model.LoginDTO;
import reactor.core.publisher.Mono;

@Service
public class ClientIMP implements IClientStore {
	
	// URL del Backend incluye contextPath definido en el servidor
		private static final String URL = "http://localhost:8010/naturaliP";

		@Autowired
		private WebClient.Builder webClient;
		
		@Override
		public int login(LoginDTO loginDTO) {
			try {
				/*
				 * aqui nos conectamos al back  directamente al controlador donde estan las rutas 
				 * el back espera recibir un dto  por eso enviamos el dto login dto
				  * */

				Mono<Integer> response = webClient.build().post().uri(URL + "/loginclient")
						.accept(MediaType.APPLICATION_JSON).body(Mono.just(loginDTO), LoginDTO.class).retrieve()
						.bodyToMono(Integer.class);

				//Aqui se captura la respuesta del back 
				return response.block();

			} catch (WebClientResponseException e) {
				e.getMessage();
				System.out.println("---->" + e.getMessage());
				return 0;
			}
		}

}
