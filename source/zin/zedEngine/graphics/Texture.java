package zin.zedEngine.graphics;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.lwjgl.opengl.EXTTextureFilterAnisotropic;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;

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
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, 0);

			if (GL.getCapabilities().GL_EXT_texture_filter_anisotropic) {
				float amount = Math.min(4f,
						GL11.glGetFloat(EXTTextureFilterAnisotropic.GL_MAX_TEXTURE_MAX_ANISOTROPY_EXT));
				GL11.glTexParameterf(GL11.GL_TEXTURE_2D, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT,
						amount);
			} else {
				System.out.println("Anisotropic Filtering not supported.");
			}
			in.close();
			GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
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
