#version 440 core

in vec3 passPosition;
in vec2 passTextureCoords;
in vec3 passNormal;

out vec4 out_Color;

uniform sampler2D textureSampler;
uniform vec3 sunPosition;
uniform vec3 sunColor;
uniform vec3 sunAttenuation;

uniform mat4 viewMatrix;

void main() 
{
	vec3 vecToLight = sunPosition - passPosition;

	float nDotl = max(dot(normalize(vecToLight), normalize(passNormal)), 0.0);

	float distance = length(vecToLight);	
	float attFac = sunAttenuation.x + (sunAttenuation.y * distance) + (sunAttenuation.z * distance * distance);
	
	vec4 brightness = (vec4(nDotl, nDotl, nDotl, 1.0) * vec4(sunColor, 1.0)) / attFac;
	
	//out_Color = brightness * texture(textureSampler, passTextureCoords);
	out_Color = texture(textureSampler, passTextureCoords);
}