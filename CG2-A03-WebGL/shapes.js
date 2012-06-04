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

Cube = function(gl , l){
	var vposition = new Float32Array([ 
									   //near and far planes
									   l/2,-l/2,l/2, -l/2,-l/2,l/2, l/2,l/2,l/2, 
									   -l/2,-l/2,l/2, l/2,l/2,l/2, -l/2,l/2,l/2,
									    
									   l/2,-l/2,-l/2, -l/2,-l/2,-l/2, l/2,l/2,-l/2, 
									   -l/2,-l/2,-l/2, l/2,l/2,-l/2, -l/2,l/2,-l/2,
									   
									   //left and right planes
									   -l/2,-l/2,l/2, -l/2,-l/2,-l/2, -l/2,l/2,l/2,
									   -l/2,l/2,l/2, -l/2,-l/2,-l/2, -l/2,l/2,-l/2,
									   
									   l/2,-l/2,l/2, l/2,-l/2,-l/2, l/2,l/2,l/2,
									   l/2,l/2,l/2, l/2,-l/2,-l/2, l/2,l/2,-l/2,
									   
									   //top and bottom planes
									   -l/2,-l/2,l/2, l/2,-l/2,l/2, -l/2,-l/2,-l/2,
									   l/2,-l/2,l/2, -l/2,-l/2,-l/2, l/2,-l/2,-l/2,
									   
									   -l/2,l/2,l/2, l/2,l/2,l/2, -l/2,l/2,-l/2,
									   l/2,l/2,l/2, -l/2,l/2,-l/2, l/2,l/2,-l/2
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

Sphere = function(gl, n, m, radius, color1, color2, matrix){
	
	console.log("sphere starts");
	
	this.radius = radius;
	this.matrix = matrix;

	var vecNr = n*m*3*3;
	
	this.x = function(u,v){
		// console.log("x: " + radius * Math.sin(u) * Math.cos(v));
		return this.radius * Math.sin(u) * Math.cos(v);
	}
	
	this.y = function(u,v){
		// console.log("y: " + radius * Math.sin(u) * Math.sin(v));
		return this.radius * Math.sin(u) * Math.sin(v);
	}
	
	this.z = function(u,v){
		// console.log("z: " + radius * Math.cos(u));
		return this.radius * Math.cos(u);
	}
	var u0, u1, v0 , v1;
	
	var index0 = 0;
	var index1 = 0;
	// var index1 = 0;
	
	var vposition = new Float32Array(vecNr * 3);
	var vcolor = new Float32Array(vecNr * 3);
	
	for(var i = 1 ; i <= n ; i++ ){
		for(var j = 1 ; j <= m ; j++ ){
			u1 = Math.PI * 2 / n * (i - 1);
			u0 = Math.PI * 2 / n * i;
			v1 = Math.PI / m * (j - 1);
			v0 = Math.PI / m * j;
			
			// console.log("u1 : " + u1 + ", u0: " + u0 + ", v1: " + v1 + ", v0: " + v0);
			
			vposition[index0++] = this.x(u0 , v1);
			vposition[index0++] = this.y(u0 , v1);
			vposition[index0++] = this.z(u0 , v1);
			
			vposition[index0++] = this.x(u1 , v1);
			vposition[index0++] = this.y(u1 , v1);
			vposition[index0++] = this.z(u1 , v1);
			
			vposition[index0++] = this.x(u0 , v0);
			vposition[index0++] = this.y(u0 , v0);
			vposition[index0++] = this.z(u0 , v0);
			
			vposition[index0++] = this.x(u1 , v1);
			vposition[index0++] = this.y(u1 , v1);
			vposition[index0++] = this.z(u1 , v1);
			
			vposition[index0++] = this.x(u0 , v0);
			vposition[index0++] = this.y(u0 , v0);
			vposition[index0++] = this.z(u0 , v0);
			
			vposition[index0++] = this.x(u1 , v0);
			vposition[index0++] = this.y(u1 , v0);
			vposition[index0++] = this.z(u1 , v0);
			
			if(i*j % 2 == 0){
					vcolor[index1++] = color1[0];
					vcolor[index1++] = color1[1];
					vcolor[index1++] = color1[2];
			
					vcolor[index1++] = color1[0];
					vcolor[index1++] = color1[1];
					vcolor[index1++] = color1[2];
			
					vcolor[index1++] = color1[0];
					vcolor[index1++] = color1[1];
					vcolor[index1++] = color1[2];
			
					vcolor[index1++] = color1[0];
					vcolor[index1++] = color1[1];
					vcolor[index1++] = color1[2];
			
					vcolor[index1++] = color1[0];
					vcolor[index1++] = color1[1];
					vcolor[index1++] = color1[2];
			
					vcolor[index1++] = color1[0];
					vcolor[index1++] = color1[1];
					vcolor[index1++] = color1[2];
				}else{
					vcolor[index1++] = color2[0];
					vcolor[index1++] = color2[1];
					vcolor[index1++] = color2[2];
			
					vcolor[index1++] = color2[0];
					vcolor[index1++] = color2[1];
					vcolor[index1++] = color2[2];
			
					vcolor[index1++] = color2[0];
					vcolor[index1++] = color2[1];
					vcolor[index1++] = color2[2];
			
					vcolor[index1++] = color2[0];
					vcolor[index1++] = color2[1];
					vcolor[index1++] = color2[2];
			
					vcolor[index1++] = color2[0];
					vcolor[index1++] = color2[1];
					vcolor[index1++] = color2[2];
			
					vcolor[index1++] = color2[0];
					vcolor[index1++] = color2[1];
					vcolor[index1++] = color2[2];
				}
			
		}
	}
	// console.log(count + ", vecanz: " + vecNr + ", length: " + vposition.length);
	for (var o=0; o < vposition.length; o++) {
	  // console.log(o + ": " + vposition[o]);
	}
	
	this.posBuffer = new VertexAttributeBuffer(gl, "vertexPosition", gl.FLOAT, 3, vposition);
	this.colorBuffer =  new VertexAttributeBuffer(gl, "vertexColor",  gl.FLOAT, 3, vcolor);
	
	this.draw = function(program) {
    
        this.posBuffer.makeActive(program);
        this.colorBuffer.makeActive(program);
        
        // perform the actual drawing of the primitive 
        // using the vertex buffer object
        program.gl.drawArrays(program.gl.TRIANGLES, 0, vecNr);

    }
}

Torus = function(gl, n, m, rad1, rad2, color1, color2){
	
	this.Radius = rad1;
	this.radius = rad2;
	
	this.color1 = color1;
	console.log(color1);
	this.color2 = color2;
	console.log(color2);
	
	if(rad1 > rad2){
	
		var vecNr = n*m*3*3; //TODO: right amount of vecs
	
		this.x = function(u,v){
			//console.log(( this.Radius + (this.radius * Math.cos(u)) ) * Math.cos(v));
			return ( this.Radius + (this.radius * Math.cos(u)) ) * Math.cos(v);
		}
	
		this.y = function(u,v){
			//console.log(( this.Radius + (this.radius * Math.cos(u)) ) * Math.sin(v));
			return ( this.Radius + (this.radius * Math.cos(u)) ) * Math.sin(v);
		}
	
		this.z = function(u,v){
			//console.log(this.radius * Math.sin(u));
			return this.radius * Math.sin(u);
		}
	
		var index0 = 0;
		var index1 = 0;
	
		//prepare the buffers
		//TODO: find out real length
		var vposition = new Float32Array(vecNr * 3);
		var vcolor = new Float32Array(vecNr * 3);
		
		//variables u,v
		var u0, u1, v0,v1;
	
		for(var i = 1 ; i <= n ; i++){
			for(var j = 1 ; j <= m ; j++) {
				//TODO: implementation
				u1 = Math.PI * 2 / n * (i - 1);
				u0 = Math.PI * 2 / n * i;
				v1 = Math.PI * 2 / m * (j - 1);
				v0 = Math.PI * 2 / m * j;
				
				// console.log("u1 : " + u1 + ", u0: " + u0 + ", v1: " + v1 + ", v0: " + v0);
			
				vposition[index0++] = this.x(u0 , v1);
				vposition[index0++] = this.y(u0 , v1);
				vposition[index0++] = this.z(u0 , v1);
			
				vposition[index0++] = this.x(u1 , v1);
				vposition[index0++] = this.y(u1 , v1);
				vposition[index0++] = this.z(u1 , v1);
			
				vposition[index0++] = this.x(u0 , v0);
				vposition[index0++] = this.y(u0 , v0);
				vposition[index0++] = this.z(u0 , v0);
			
				vposition[index0++] = this.x(u1 , v1);
				vposition[index0++] = this.y(u1 , v1);
				vposition[index0++] = this.z(u1 , v1);
			
				vposition[index0++] = this.x(u0 , v0);
				vposition[index0++] = this.y(u0 , v0);
				vposition[index0++] = this.z(u0 , v0);
			
				vposition[index0++] = this.x(u1 , v0);
				vposition[index0++] = this.y(u1 , v0);
				vposition[index0++] = this.z(u1 , v0);
				
				if(i*j % 2 == 0){
					vcolor[index1++] = color1[0];
					vcolor[index1++] = color1[1];
					vcolor[index1++] = color1[2];
			
					vcolor[index1++] = color1[0];
					vcolor[index1++] = color1[1];
					vcolor[index1++] = color1[2];
			
					vcolor[index1++] = color1[0];
					vcolor[index1++] = color1[1];
					vcolor[index1++] = color1[2];
			
					vcolor[index1++] = color1[0];
					vcolor[index1++] = color1[1];
					vcolor[index1++] = color1[2];
			
					vcolor[index1++] = color1[0];
					vcolor[index1++] = color1[1];
					vcolor[index1++] = color1[2];
			
					vcolor[index1++] = color1[0];
					vcolor[index1++] = color1[1];
					vcolor[index1++] = color1[2];
				}else{
					vcolor[index1++] = color2[0];
					vcolor[index1++] = color2[1];
					vcolor[index1++] = color2[2];
			
					vcolor[index1++] = color2[0];
					vcolor[index1++] = color2[1];
					vcolor[index1++] = color2[2];
			
					vcolor[index1++] = color2[0];
					vcolor[index1++] = color2[1];
					vcolor[index1++] = color2[2];
			
					vcolor[index1++] = color2[0];
					vcolor[index1++] = color2[1];
					vcolor[index1++] = color2[2];
			
					vcolor[index1++] = color2[0];
					vcolor[index1++] = color2[1];
					vcolor[index1++] = color2[2];
			
					vcolor[index1++] = color2[0];
					vcolor[index1++] = color2[1];
					vcolor[index1++] = color2[2];
				}
			}
		}
	
		this.posBuffer = new VertexAttributeBuffer(gl, "vertexPosition", gl.FLOAT, 3, vposition);
		this.colorBuffer =  new VertexAttributeBuffer(gl, "vertexColor",  gl.FLOAT, 3, vcolor);
	
		this.draw = function(program) {
   	 
       	 this.posBuffer.makeActive(program);
       	 this.colorBuffer.makeActive(program);
        
        	// perform the actual drawing of the primitive 
        	// using the vertex buffer object
        	program.gl.drawArrays(program.gl.TRIANGLES, 0, vecNr);

    	}
   	}
}
