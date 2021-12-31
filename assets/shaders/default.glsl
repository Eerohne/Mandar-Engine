#type vertex
#version 410 core

layout(location = 0) in vec3 aPos;

out vec3 vPos;

void main() {
    vPos = aPos;
    gl_Position = vec4(aPos, 1.0);
}

#type fragment
#version 400 core

in vec3 vPos;

layout(location = 0) out vec4 color;

void main() {
    color = vec4(vPos *0.5 + 0.5, 1.0);
}

