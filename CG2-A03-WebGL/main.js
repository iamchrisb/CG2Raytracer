/*
 * WebGL / Javascript tutorial.
 * Author: Hartmut Schirmacher, hschirmacher.beuth-hochschule.de
 * (C)opyright 2011 by Hartmut Schirmacher, all rights reserved. 
 *
 */


/* 

   The "Main" Function is an event handler that is called 
   whenever the HTML document is loaded/refreshed
*/

window.onload = function () {

    // initialize WebGL and compile shader program
    var canvas = window.document.getElementById("webgl_canvas");
    var gl = initWebGL("webgl_canvas");
    var vs = getShaderSource("vert_shader");
    var fs = getShaderSource("frag_shader");
    var prog = new Program(gl, vs, fs);
    
    // theScene is a global variable; it is accessed by the event handlers
    theScene = new SimpleScene(prog, [0.0 ,0.0, 0.0, 1.0]);
    
    var imatrix = mat4.identity();
    // add an object to the scene
    // theScene.addShape(new TriangleFan(gl));
    // theScene.addShape(new Cube(gl , 0.8));
    var color1 = new Array(1,1,1);
    var color2 = new Array(0.6,0.6,0.6);
    // theScene.addShape(new Sphere(gl , 50 , 50 , 0.5 , color1 , new Array(1,0,0) , imatrix));
    // theScene.addShape(new Torus(gl , 20, 20, 1, 0.4 , color1, color2 ));
    // theScene.addShape(new TriangleStrip(gl));
    
    // set the camera's viewpoint and viewing direction
    theScene.camera.lookAt([0,2,4], [0,0,0], [0,1,0]);
    
    // use the values in the HTML form to initialize the camera's projection
    updateCamera(theScene); 
    
    // the SceneExporer is also a global object; 
    // it handles events to manipulate the scene
    theExplorer = new SceneExplorer(canvas,true,theScene);
    
    //events
    $("#properties-sphere").hide();
    $("#properties-torus").hide();
    $("#properties-cube").hide();
    
    var sr = document.getElementById("sphere-radio");
    	sr.onclick = function(event){
    	$("#properties-sphere").show();
   		$("#properties-torus").hide();
    	$("#properties-cube").hide();
    }
    
    var st = document.getElementById("torus-radio");
    st.onclick = function(event){
    	$("#properties-sphere").hide();
   		$("#properties-torus").show();
    	$("#properties-cube").hide();
    }
    
    var sc = document.getElementById("cube-radio");
    sc.onclick = function(event){
    	$("#properties-sphere").hide();
   		$("#properties-torus").hide();
    	$("#properties-cube").show();
    }
    
    var sb = document.getElementById("sphere-button");
    sb.onclick = function(event){
    	var sform = document.forms["sphere-form"];
    	if(sform){
    		var fn = parseFloat(sform.elements["n"].value);
    		var fm = parseFloat(sform.elements["m"].value);
    		var fr = parseFloat(sform.elements["radius"].value);
    		
    		var color1 = new Array(1,1,1);
    		var color2 = new Array(0.6,0.6,0.6);
    		
    		console.log(gl);
    		theScene.addShape( new Sphere(gl , fn , fm , fr , color1 , color2 , mat4.identity()));
    		
    		updateCamera(theScene);
    	}
    }
    
    var sbr = document.getElementById("sphere-remove");
    sbr.onclick = function(event){
    	theScene.removeShape();
    	updateCamera(theScene);
    }
    
    var tb = document.getElementById("torus-button");
    tb.onclick = function(event){
    	var tform = document.forms["torus-form"];
    	console.log("click");
    	if(tform){
    		var fn = parseFloat(tform.elements["n"].value);
    		var fm = parseFloat(tform.elements["m"].value);
    		var fR = parseFloat(tform.elements["Radius"].value);
    		var fr = parseFloat(tform.elements["radius"].value);
    		
    		var color1 = new Array(1,1,1);
    		var color2 = new Array(0.6,0.6,0.6);
    		
    		theScene.addShape( new Torus(gl , fn , fm , fR , fr , color1 , color2 ));
    		updateCamera(theScene);
    	}
    }
    
    var tbr = document.getElementById("torus-remove");
    tbr.onclick = function(event){
    	theScene.removeShape();
    	updateCamera(theScene);
    }
    
    var cb = document.getElementById("cube-button");
    cb.onclick = function(event){
    	var cform = document.forms["cube-form"];
    	if(cform){
    		var fl = parseFloat(cform.elements["len"].value);
    		console.log(fl);
    		theScene.addShape( new Cube(gl , fl));
    		updateCamera(theScene);
    	}
    }
    
    var cbr = document.getElementById("cube-remove");
    cbr.onclick = function(event){
    	theScene.removeShape();
    	updateCamera(theScene);
    }
};


/*
    Event handler called whenever values in the 
    "cameraParameters" form have been updated.
    
    The function simply reads values from the HTML form
    and calls the respective functions of the scene's 
    camera object.
*/
     
updateCamera = function(scene) {

    var f = document.forms["cameraParameters"];
    var cam = scene.camera;
    
    if(!f) {
        window.console.log("ERROR: Could not find HTML form named 'projectionParameters'.");
        return;
    }
    
    // check which projection type to use (0 = perspective; 1 = ortho)
    if(f.elements["projection_type"][0].checked == true) {
    
        // perspective projection: fovy, aspect, near, far
        if(!cam)
            alert("Cannot find camera object!!!");

        // update camera - set up perspective projection
        cam.perspective(parseFloat(f.elements["fovy"].value), 
                        1.0, // aspect
                        parseFloat(f.elements["znear"].value),
                        parseFloat(f.elements["zfar"].value)   );
        scene.draw();
        
    } else {

        // update camera - set up orthographic projection
        cam.orthographic(parseFloat(f.elements["left"].value),  parseFloat(f.elements["right"].value),
                         parseFloat(f.elements["bot"].value),   parseFloat(f.elements["top"].value),
                         parseFloat(f.elements["front"].value), parseFloat(f.elements["back"].value)  );
        scene.draw();
    }
  
}


