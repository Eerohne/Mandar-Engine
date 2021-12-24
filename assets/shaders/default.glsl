#type vertex
#version 400 core

in vec3 aPos;


void main() {
    gl_Position = vec4(aPos, 1.0);
}

#type fragment
#version 400 core


out vec4 color;

void main() {
    color = vec4(1, 0.5, 0.5, 1.0);
}

