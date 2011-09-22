varying vec3 EyeDir;// direction of the light
varying vec3 LightDir;// direction of the eye

attribute vec3 Tangent;
attribute int FlipBinormal;

void main() {
	gl_Position = ftransform();
	gl_TexCoord[0] = gl_TextureMatrix[0] * gl_MultiTexCoord0;
	
	vec3 n = normalize(gl_NormalMatrix * gl_Normal);
	vec3 t = normalize(gl_NormalMatrix * Tangent);
	vec3 b;
	if (FlipBinormal < 0.0) {
		b = cross(t, n);
	} else {
		b = cross(n, t);
	}
	mat3 TBN = mat3(t, b, n);
	
	EyeDir = vec3(gl_ModelViewMatrix * gl_Vertex);
	LightDir = EyeDir - vec3(gl_LightSource[0].position);
	
	EyeDir = normalize(EyeDir * TBN);
	LightDir = normalize(LightDir * TBN);
}
