uniform sampler2D ColorMap;
uniform sampler2D CombinedMap;

uniform float HeightFactor;//0.04;
uniform float HeightBias;//0.02;

varying vec3 EyeDir;
varying vec3 LightDir;//normalize

void main() {
	EyeDir = normalize(EyeDir);
	float height = texture2D(CombinedMap, gl_TexCoord[0].xy).a * HeightFactor - HeightBias;
	vec2 lookupCoords = gl_TexCoord[0].xy - EyeDir.xy * height;
	
	vec4 surfaceColor = texture2D(ColorMap, gl_TexCoord[0].xy);
	vec3 normal = vec3(texture2D(CombinedMap, lookupCoords)) * 2.0 - 1.0;//normalize
	
	float diffuseFactor = max(-dot(normal, LightDir), 0.0);
	
	vec3 reflectDir = reflect(LightDir, normal);
	float specularFactor = clamp(-dot(EyeDir, reflectDir), 0.0, 1.0);//max(-dot(EyeDir, reflectDir), 0.0);
	specularFactor = pow(specularFactor, gl_FrontMaterial.shininess);
	
	/*
	// debugging version
	vec4 sceneColor = vec4(0.0, 0.0, 0.0, 1.0);
	vec4 ambient = vec4(0.0, 0.0, 0.0, 1.0);
	vec4 diffuse = vec4(0.8, 0.8, 0.8, 1.0);
	vec4 specular = vec4(0.8, 0.8, 0.8, 1.0);
	
	gl_FragColor = (sceneColor + ambient
					+ diffuse * diffuseFactor) * surfaceColor;
	vec4 specularColor = surfaceColor;
	gl_FragColor += specular * specularFactor * specularColor;
	*/
	
	// working version
	gl_FragColor = (gl_FrontLightModelProduct.sceneColor + gl_FrontLightProduct[0].ambient
					+ gl_FrontLightProduct[0].diffuse * diffuseFactor) * surfaceColor;
	vec4 specularColor = surfaceColor;
	gl_FragColor += gl_FrontLightProduct[0].specular * specularFactor * specularColor;
}
