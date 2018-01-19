package zin.zedEngine.graphics;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import de.matthiasmann.twl.utils.PNGDecoder;

public class Texture {

	private int textureID;

	public Texture(String fileName) {
		try {
			InputStream in = Class.class.getResourceAsStream("/textures/" + fileName + ".png");
			PNGDecoder decoder = new PNGDecoder(in);
			ByteBuffer buf = ByteBuffer.allocateDirect(3 * decoder.getWidth() * decoder.getHeight());
			decoder.decode(buf, decoder.getWidth() * 3, PNGDecoder.Format.RGB);
			buf.flip();

			textureID = GL11.glGenTextures();
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, decoder.getWidth(), decoder.getHeight(), 0,
					GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, buf);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getTextureID() {
		return textureID;
	}

	public void bindTexture(int textureBank) {
		GL13.glActiveTexture(textureBank);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
	}

	public void unbindTexture() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
	public void cleanUp() {
		GL11.glDeleteTextures(textureID);
	}

}
