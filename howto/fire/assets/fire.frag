#version 300 es

precision mediump float;

vec2 UV;
vec2 SIZE;
vec4 COLOR;
float TIME;
vec2 SCREEN_COORDS;

//<indigo-fragment>

//-- noise: https://www.iquilezles.org/www/articles/gradientnoise/gradientnoise.htm --
vec2 hash( in vec2 x ) {
    const vec2 k = vec2(0.3183099, 0.3678794);
    x = x * k + k.yx;
    return -1.0 + 2.0 * fract( 16.0 * k * fract(x.x * x.y * (x.x + x.y)));
}

vec3 calcNoise( in vec2 p ) {
    vec2 i = floor( p );
    vec2 f = fract( p );
    vec2 u = f*f*(3.0-2.0*f);
    vec2 du = 6.0*f*(1.0-f);
    vec2 ga = hash( i + vec2(0.0,0.0) );
    vec2 gb = hash( i + vec2(1.0,0.0) );
    vec2 gc = hash( i + vec2(0.0,1.0) );
    vec2 gd = hash( i + vec2(1.0,1.0) );
    
    float va = dot( ga, f - vec2(0.0,0.0) );
    float vb = dot( gb, f - vec2(1.0,0.0) );
    float vc = dot( gc, f - vec2(0.0,1.0) );
    float vd = dot( gd, f - vec2(1.0,1.0) );

    return vec3( va + u.x*(vb-va) + u.y*(vc-va) + u.x*u.y*(va-vb-vc+vd),
                 ga + u.x*(gb-ga) + u.y*(gc-ga) + u.x*u.y*(ga-gb-gc+gd) +
                 du * (u.yx*(va-vb-vc+vd) + vec2(vb,vc) - va));
}
//-- / noise --

// SDF circle https://www.iquilezles.org/www/articles/distfunctions2d/distfunctions2d.htm
float sdfCircle(vec2 p, float r) {
  return length(p) - r;
}
// SDF

layout (std140) uniform FireData {
  float OFFSET;
  vec3 COLOR_OUTER;
  vec3 COLOR_INNER;
  vec3 COLOR_CENTER;
};

void fragment() {
  float octaves = 8.0;
  float timeMultiple = 7.0;
  float noiseAmount = calcNoise(vec2(octaves * UV.x, (octaves * UV.y) + ((TIME + OFFSET) * timeMultiple))).x;
  float yGradient = clamp(0.7 - UV.y, 0.0, 1.0) * 0.6;
  vec2 sdfNoise = vec2(noiseAmount * 0.1, noiseAmount * 2.5 * yGradient);

  vec2 p1 = (UV - vec2(0.5, 0.7)) + sdfNoise;
  vec2 p2 = (UV - vec2(0.5, 0.775)) + sdfNoise;
  vec2 p3 = (UV - vec2(0.5, 0.85)) + sdfNoise;

  float amountOuter = step(sdfCircle(p1, 0.25), 0.0);
  float amountInner = step(sdfCircle(p2, 0.175), 0.0);
  float amountCenter = step(sdfCircle(p3, 0.1), 0.0);

  vec3 outer = COLOR_OUTER * amountOuter;
  vec3 inner = COLOR_INNER * amountInner;
  vec3 center = COLOR_CENTER * amountCenter;

  COLOR = vec4(outer + inner + center, amountOuter);
}

//</indigo-fragment>

