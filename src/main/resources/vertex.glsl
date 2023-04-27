#version 330

layout (location=0) in vec3 position;

void HelloWorld() {
    gl_Position = vec4(position, 1.0);
}
