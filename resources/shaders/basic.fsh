#version 440 core

in vec2 passTextureCoords;

out vec4 out_Color;

uniform sampler2D textureSampler;

void main() 
{
	out_Color = vec4(1,0,1,1);
	//out_Color = texture(textureSampler, passTextureCoords);
}