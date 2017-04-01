attribute vec4 a_position; //позиция вершины
attribute vec4 a_color; //цвет вершины
attribute vec2 a_texCoord0; //координаты текстуры

uniform vec2 u_player;
uniform mat4 u_projTrans;  //матрица, которая содержим данные для преобразования проекции и вида

varying vec4 v_color;  //цвет который будет передан в фрагментный шейдер
varying vec2 v_texCoords;  //координаты текстуры
varying vec2 v_position;

void main(){
    v_color=a_color;
    v_texCoords = a_texCoord0;
    v_position = a_position.xy;

    gl_Position =  u_projTrans * a_position; 
}