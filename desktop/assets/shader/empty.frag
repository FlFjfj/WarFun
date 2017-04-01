varying vec4 v_color;
varying vec2 v_texCoords;
varying vec2 v_position;

uniform vec2 u_player;
uniform sampler2D u_texture;
uniform sampler2D u_texture1;

void main(){

    const float blur_len = 100.;
    const float radius = 200.;

    vec4 color;
    float len = length(u_player - v_position);
    if(len > radius)
	 color = v_color * texture2D(u_texture1, v_texCoords);
    else{
	 color = v_color * texture2D(u_texture, v_texCoords);
    }

    len = len - radius;
    if(len < blur_len && len > 0)
	color.xyz = v_color * 
	( mix(0., 1., (blur_len - len) / blur_len) * texture2D(u_texture, v_texCoords).xyz + 
	  mix(0., 1., len / blur_len) * texture2D(u_texture1, v_texCoords).xyz);

    gl_FragColor = color;
}