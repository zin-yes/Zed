#version 440 core

in vec3 position;
in vec2 textureCoords;
in vec3 normal;

out vec3 passPosition;
out vec2 passTextureCoords;
out vec3 passNormal;
out vec3 cameraDir;

uniform mat4 transformationMatrix;
uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;

void main()
{
	gl_Position = projectionMatrix * viewMatrix * transformationMatrix * vec4(position, 1.0);
	
	cameraDir = (inverse(viewMatrix) * vec4(0.0f, 0.0f, 0.0f, 1.0f)).xyz - (transformationMatrix * vec4(position, 1.0)).xyz;
	
	passPosition = position;
	passTextureCoords = textureCoords;
	passNormal = normal;
}