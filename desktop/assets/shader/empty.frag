varying vec4 v_color;
varying vec2 v_texCoords;
varying vec2 v_position;

uniform float u_time;
uniform vec2 u_player0;
uniform vec2 u_player1;
uniform mat4 u_projTrans;
uniform sampler2D u_texture;
uniform sampler2D u_texture1;
uniform float radius0;
uniform float radius1;

vec3 rgb2hsv(vec3 c)
{
    vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);
    vec4 p = mix(vec4(c.bg, K.wz), vec4(c.gb, K.xy), step(c.b, c.g));
    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));

    float d = q.x - min(q.w, q.y);
    float e = 1.0e-10;
    return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);
} 

vec3 hsv2rgb(vec3 c)
{
    vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);
    vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);
    return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);
}


void main(){

    const float blur_len = 50.;

    vec4 bright_color = texture2D(u_texture, v_texCoords);
    vec3 hsv_color = rgb2hsv(bright_color.rgb);
    hsv_color.r = (hsv_color.r + u_time / 4.) - floor(hsv_color.r + u_time / 4.);
    bright_color.rgb = hsv2rgb(hsv_color);

    vec4 color;
    float len0 = length(u_player0 - v_position);
    float len1 = length(u_player1 - v_position);

    if(len0 < radius0 || len1 < radius1)
	 color = bright_color;
    else{
	 color = texture2D(u_texture1, v_texCoords);
    }

    /*vec2 dir;
    if(len0 < len1)
        dir = -(u_player0 - v_position).nor;
    else
        dir = -(u_player1 - v_position).nor*/       
    //float angle = 

    float len = min((len0 - radius0), (len1 - radius1));
    float flen = blur_len * abs(cos(u_time * 5.));
    if(len < flen && len > 0.)
	color.xyz =  
	  mix(0., 1., (flen - len) / flen) * bright_color.xyz + 
	  mix(0., 1., len / flen) * texture2D(u_texture1, v_texCoords).xyz;

    gl_FragColor = color;
}
