varying vec4 v_color;
varying vec2 v_texCoords;
varying vec2 v_position;

//uniform int u_rainbowed;
uniform vec2 u_player;
uniform mat4 u_projTrans;
uniform sampler2D u_texture;
uniform sampler2D u_texture1;

void main(){

    const float blur_len = 100.;
    const float radius = 200.;

    vec4 color;
    float len = length(u_player - v_position);
    if(len < radius || v_color.a != 0)
	 color = texture2D(u_texture, v_texCoords);
    else{
	 color = texture2D(u_texture1, v_texCoords);
    }

    len = len - radius;
    if(len < blur_len && len > 0. && v_color.a == 0)
	color.xyz =  
	( mix(0., 1., (blur_len - len) / blur_len) * texture2D(u_texture, v_texCoords).xyz + 
	  mix(0., 1., len / blur_len) * texture2D(u_texture1, v_texCoords).xyz);

    gl_FragColor = color;
}