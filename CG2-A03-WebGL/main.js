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
    // theScene.addShape(new Cube(gl , 2));
    var color1 = new Array(1,1,1);
    var color2 = new Array(0.6,0.6,0.6);
    theScene.addShape(new Sphere(gl , 50 , 50 , 0.5 , color1 , new Array(1,0,0) , imatrix));
    theScene.addShape(new Torus(gl , 20, 20, 1, 0.5 , color1, color2 ));
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
    	console.log("sphere radio click");
    	$("#properties-sphere").show();
   		$("#properties-torus").hide();
    	$("#properties-cube").hide();
    }
    
    var st = document.getElementById("torus-radio");
    st.onclick = function(event){
    	console.log("torus radio click");
    	$("#properties-sphere").hide();
   		$("#properties-torus").show();
    	$("#properties-cube").hide();
    }
};


/*
    Event handler called whenever values in the 
    "cameraParameters" form have been updated.
    
    The function simply reads values from the HTML form
    and calls the respective functions of the scene's 
    camera object.
*/

printCode = function(event){
	console.log("ich werde ausgefuehrt du flachwixer :)");
	var chkText = event.srcElement.parentElement.lastChild.innerHTML; 
	console.log(chkText);
}
     
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


