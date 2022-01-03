#type vertex
#version 410 core

layout(location = 0) in vec3 aPos;
layout(location = 1) in vec4 aCol;

out vec3 vPos;
out vec4 vCol;

void main() {
    vPos = aPos;
    vCol = aCol;
    gl_Position = vec4(aPos, 1.0);
}

#type fragment
#version 410 core

in vec3 vPos;
in vec4 vCol;

layout(location = 0) out vec4 color;

void main() {
    color = vCol;//vec4(vPos *0.5 + 0.5, 1.0);
}

