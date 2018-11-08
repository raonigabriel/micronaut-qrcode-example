package com.github.raonigabriel.micronaut;

import javax.inject.Inject;

import org.slf4j.LoggerFactory;

import io.micronaut.http.*;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.hateos.*;
import io.micronaut.runtime.Micronaut;
import io.reactivex.Single;

@Controller("/qrcode")
public class MicronautExampleApp {

	@Inject
	ImageService imageService;

	public static void main(String[] args) {
		LoggerFactory.getLogger(io.micronaut.runtime.Micronaut.class).info("Starting HTTP server...");
		Micronaut.run(MicronautExampleApp.class);
	}

	@Get(produces = "image/png")
	Single<HttpResponse<byte[]>> getQRCode(@QueryValue String text) {
		return Single.create(emmiter -> {
			try {
				emmiter.onSuccess(HttpResponse.ok(imageService.generateQRCode(text, 256, 256))
						.header(HttpHeaders.CACHE_CONTROL, "max-age=1800"));
			} catch (Exception ex) {
				emmiter.onError(ex);
			}
		});
	}

	@Delete
	void deleteAllCachedImages() {
		imageService.purgeCache();
	}

	@Error
	HttpResponse<JsonError> badRequest(HttpRequest<?> request, IllegalArgumentException ex) { 
		return HttpResponse.badRequest(new JsonError(ex.getMessage())
			.link(Link.SELF, Link.of(request.getUri()))); 
	}
	
	@Error
	HttpResponse<JsonError> serverError(HttpRequest<?> request, RuntimeException ex) { 
		return HttpResponse.serverError(new JsonError(ex.getMessage())
			.link(Link.SELF, Link.of(request.getUri()))); 
	}

}