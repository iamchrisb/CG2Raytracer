/*
 * WebGL / Javascript tutorial.
 * Author: Hartmut Schirmacher, hschirmacher.beuth-hochschule.de
 * (C)opyright 2012 by Hartmut Schirmacher, all rights reserved. 
 *
 */


/* 

   Class:  Triangle
   The triangle consists of three vertices. 
    
   Parameters to the constructor:
   - program is a Program object that knows which vertex attributes 
     are expected by its shaders
   
*/ 

Triangle = function(gl, material) {

    // the position vectors of the three vertices
    var vposition = new Float32Array( [ 0,1,0,  -1,-1,0, 1,-1,0 ]);
    this.posBuffer = new VertexAttributeBuffer(gl, "vertexPosition", gl.FLOAT, 3, vposition);
    
    // the color values for the three vertices
    var vcolor    = new Float32Array( [ 1,0,0,  0,1,0,   0,0,1 ]);
    this.colorBuffer =  new VertexAttributeBuffer(gl, "vertexColor",  gl.FLOAT, 3, vcolor);
    
    /* 
       Method: draw using a vertex buffer object
    */
    this.draw = function(program) {
    
        this.posBuffer.makeActive(program);
        
        // perform the actual drawing of the primitive 
        // using the vertex buffer object
        program.gl.drawArrays(program.gl.TRIANGLES, 0, 3);

    }
    
}        
    

/* 

   Class:  TriangleFan
   A little "hat" around a center vertex. 
    
   Parameters to the constructor:
   - program is a Program object that knows which vertex attributes 
     are expected by its shaders
   
*/ 

TriangleFan = function(gl, material) {

    // the position vectors of the vertices
    var vposition = new Float32Array( [ 0,0,1,        0,1,0,       -0.7,0.7,0, 
                                        -1,0,0,      -0.7,-0.7,0,  0,-1,0, 
                                        0.7,-0.7,0,  1.0,0,0,      0.7,0.7,0]);
    this.posBuffer = new VertexAttributeBuffer(gl, "vertexPosition", gl.FLOAT, 3, vposition);

    // the color values for each vertex
    var vcolor    = new Float32Array( [ 1,1,1,  1,0,0,  0,1,0,      
                                        0,0,1,  1,0,0,  0,1,0,  
                                        0,0,1,  1,0,0,  0,1,0,    ]);
    this.colorBuffer =  new VertexAttributeBuffer(gl, "vertexColor",  gl.FLOAT, 3, vcolor);
    
    /* 
       Method: draw using a vertex buffer object
    */
    this.draw = function(program) {
    
        this.posBuffer.makeActive(program);
        this.colorBuffer.makeActive(program);
        
        // perform the actual drawing of the primitive 
        // using the vertex buffer object
        program.gl.drawArrays(program.gl.TRIANGLE_FAN, 0, 9);

    }
}    

Cube = function(gl){
	var vposition = new Float32Array([ 
									   //near and far planes
									   1,-1,1, -1,-1,1, 1,1,1, 
									   -1,-1,1, 1,1,1, -1,1,1,
									    
									   1,-1,-1, -1,-1,-1, 1,1,-1, 
									   -1,-1,-1, 1,1,-1, -1,1,-1,
									   
									   //left and right planes
									   -1,-1,1, -1,-1,-1, -1,1,1,
									   -1,1,1, -1,-1,-1, -1,1,-1,
									   
									   1,-1,1, 1,-1,-1, 1,1,1,
									   1,1,1, 1,-1,-1, 1,1,-1,
									   
									   //top and bottom planes
									   -1,-1,1, 1,-1,1, -1,-1,-1,
									   1,-1,1, -1,-1,-1, 1,-1,-1,
									   
									   -1,1,1, 1,1,1, -1,1,-1,
									   1,1,1, -1,1,-1, 1,1,-1
									   ]);
	this.posBuffer = new VertexAttributeBuffer(gl, "vertexPosition", gl.FLOAT, 3, vposition);
	
	var vcolor = new Float32Array([0.8,0.8,0.8, 0.8,0.8,0.8, 0.8,0.8,0.8,
								   0.8,0.8,0.8, 0.8,0.8,0.8, 0.8,0.8,0.8,  
								   0.5,0.5,0.5, 0.5,0.5,0.5, 0.5,0.5,0.5, 
								   0.5,0.5,0.5, 0.5,0.5,0.5, 0.5,0.5,0.5,
								   1.0,0.0,0.0, 1.0,0.0,0.0, 1.0,0.0,0.0,
								   1.0,0.0,0.0, 1.0,0.0,0.0, 1.0,0.0,0.0,
								   1.0,1.0,0.0, 1.0,1.0,0.0, 1.0,1.0,0.0,
								   1.0,1.0,0.0, 1.0,1.0,0.0, 1.0,1.0,0.0,
								   0.0,1.0,0.0, 0.0,1.0,0.0, 0.0,1.0,0.0,
								   0.0,1.0,0.0, 0.0,1.0,0.0, 0.0,1.0,0.0,
								   0.0,0.8,0.0, 0.0,0.8,0.0, 0.0,0.8,0.0,
								   0.0,0.8,0.0, 0.0,0.8,0.0, 0.0,0.8,0.0
								   ]);
	this.colorBuffer = new VertexAttributeBuffer(gl, "vertexColor", gl.FLOAT, 3, vcolor);
	
	this.draw = function(program){
		this.posBuffer.makeActive(program);
		this.colorBuffer.makeActive(program);
		
		program.gl.drawArrays(program.gl.TRIANGLES, 0, 36);
	}
}    
    
TriangleStrip = function(gl) {

    // the position vectors of the vertices
    var vposition = new Float32Array( [ 0,0,1,        0,1,0,       -0.7,0.7,0, 
                                        -1,0,0,      -0.7,-0.7,0,  0,-1,0, 
                                        0.7,-0.7,0,  1.0,0,0,      0.7,0.7,0]);
    this.posBuffer = new VertexAttributeBuffer(gl, "vertexPosition", gl.FLOAT, 3, vposition);

    // the color values for each vertex
    var vcolor    = new Float32Array( [ 1,1,1,  1,0,0,  0,1,0,      
                                        0,0,1,  1,0,0,  0,1,0,  
                                        0,0,1,  1,0,0,  0,1,0,    ]);
    this.colorBuffer =  new VertexAttributeBuffer(gl, "vertexColor",  gl.FLOAT, 3, vcolor);
    /* 
       Method: draw using a vertex buffer object
    */
    this.draw = function(program) {
    
        this.posBuffer.makeActive(program);
        this.colorBuffer.makeActive(program);
        
        // perform the actual drawing of the primitive 
        // using the vertex buffer object
        program.gl.drawArrays(program.gl.TRIANGLE_FAN, 0, 9);

    }
} 

Sphere = function(){
	
}
