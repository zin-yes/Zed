#version 440 core

in vec3 cameraDir;
in vec3 passPosition;
in vec2 passTextureCoords;
in vec3 passNormal;

out vec4 out_Color;

uniform sampler2D textureSampler;

uniform vec3 spotLightPosition[14];
uniform vec3 spotLightColor[14];
uniform vec3 spotLightAttenuation[14];

uniform vec3 directionalLightDirection[4];
uniform vec3 directionalLightColor[4];

uniform float textureMultiplier;
uniform float reflectivity;
uniform float specularDampening;

void main()
{
	vec4 totalDiffuse = vec4(0, 0, 0, 1);
	vec4 totalSpecular = vec4(0, 0, 0, 1);
	
	for (int i = 0; i < 14; i++) 
	{
		vec3 vecToLight = spotLightPosition[i] - passPosition;

		float nDotl = max(dot(normalize(vecToLight), normalize(passNormal)), 0.15);
		
		float distance = length(vecToLight);
		float attFac = spotLightAttenuation[i].x + (spotLightAttenuation[i].y * distance) + (spotLightAttenuation[i].z * distance * distance);
		
		totalDiffuse = totalDiffuse + ((vec4(nDotl, nDotl, nDotl, 1.0) * vec4(spotLightColor[i], 1.0)) / attFac);
	}
	
	for (int i = 0; i < 4; i++) 
	{
		float nDotl2 = max(dot(normalize(directionalLightDirection[i]), normalize(passNormal)), 0.0);
		totalDiffuse = totalDiffuse + (vec4(nDotl2, nDotl2, nDotl2, 1.0) * vec4(directionalLightColor[i], 1.0));
	}
	
	out_Color = totalDiffuse * texture(textureSampler, passTextureCoords/textureMultiplier);
}