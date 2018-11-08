package com.github.raonigabriel.micronaut;

import java.io.*;
import javax.inject.Singleton;
import org.slf4j.*;
import com.google.zxing.*;
import com.google.zxing.client.j2se.*;
import com.google.zxing.common.BitMatrix;
import io.micronaut.cache.annotation.*;
import io.micronaut.core.util.StringUtils;
import io.micronaut.scheduling.annotation.Scheduled;

@Singleton
@CacheConfig(cacheNames = "qr-code-cache")
public class ImageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);

	@Cacheable
	public byte[] generateQRCode(String text, int width, int height) {
 
		if (StringUtils.isEmpty(text)) {
			throw new IllegalArgumentException("text must not be empty");
		}
		if (width <= 0) {
			throw new IllegalArgumentException("width must be greater than zero");
		}	
		if (width <= 0) {
			throw new IllegalArgumentException("height must be greater than zero");
		}

		try {
			LOGGER.info("Will generate image  text=[{}], width=[{}], height=[{}]", text, width, height);
			ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
			BitMatrix matrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
			MatrixToImageWriter.writeToStream(matrix, "png", baos, new MatrixToImageConfig());
			return baos.toByteArray();
		} catch (IOException | WriterException ex) {
			throw new RuntimeException(ex);
		}
		
	}

	@Scheduled(initialDelay = "30m", fixedDelay = "30m")
	@CacheInvalidate(all = true)
	public void purgeCache() {
		LOGGER.info("Purging cache");
	}

}