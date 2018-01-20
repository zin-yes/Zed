#version 440 core

in vec3 passPosition;
in vec2 passTextureCoords;
in vec3 passNormal;

out vec4 out_Color;

uniform sampler2D textureSampler;

uniform vec3 spotLightPosition[14];
uniform vec3 spotLightColor[14];
uniform vec3 spotLightAttenuation[14];

uniform vec3 directionalLightPosition[14];
uniform vec3 directionalLightColor[14];
uniform vec3 directionalLightAttenuation[14];


void main() 
{
	vec4 totalDiffuse;
	for (int i = 0; i < 14; i++) {
		vec3 vecToLight = spotLightPosition[i] - passPosition;
		
		float nDotl = max(dot(normalize(vecToLight), normalize(passNormal)), 0.0);
		
		float distance = length(vecToLight);
		float attFac = spotLightAttenuation[i].x + (spotLightAttenuation[i].y * distance) + (spotLightAttenuation[i].z * distance * distance);
			
		totalDiffuse = totalDiffuse + ((vec4(nDotl, nDotl, nDotl, 1.0) * vec4(spotLightColor[i], 1.0)) / attFac);
	}
	out_Color = totalDiffuse * texture(textureSampler, passTextureCoords);
	//out_Color = texture(textureSampler, passTextureCoords);
}