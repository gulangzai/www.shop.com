
    // some global vars
var _viewer = null;     // the viewer
var _savedGlobalCamera = null;
var _views2D = null;
var _views3D = null;
var _savedViewerStates = [];

    // setup for Local
var _viewerEnv = "Local";
//var _myAuthToken = new MyAuthToken("LOCAL");


    // when we switch models, we want to reset the UI for the Try It form or it might have left over
    // data about selection sets and stuff that isn't valid anymore.
function blankOutTryItForm() {
    $("div.lmvDbg_swap").empty();
    $("code.language-markup").empty();
}


    // As of build 0.1.204, you can switch sheets more directly instead of completely
    // initializing a new viewer.  But, we stil want to reset our global vars that keep
    // track of things like our current selection set.
function switchSheet() {
    
    if (_viewer !== null) {
        _viewer.tearDown();     // delete everything associated with the current loaded asset
        _savedGlobalCamera = null;
        _savedViewerStates = [];
    }

    _viewer.setUp();    // set it up again for a new asset to be loaded
}


    // initialize the viewer into the HTML placeholder
function initializeViewer() {
    
    if (_viewer !== null) {
        //_viewer.uninitialize();
        _viewer.finish();
        _viewer = null;
        _savedGlobalCamera = null;
        _savedViewerStates = [];
    }

    var viewerElement = document.getElementById("viewer");  // placeholder in HTML to stick the viewer
    
    _viewer = new Teratek.Viewing.Private.GuiViewer3D(viewerElement, {});
   
    var retCode = _viewer.initialize();
    if (retCode !== 0) {
        alert("ERROR: Couldn't initialize viewer!");
        console.log("ERROR Code: " + retCode);      // TBD: do real error handling here
    }
    
}



    // for now, just simple diagnostic functions to make sure we know what is happing
function loadViewSuccessFunc()
{
    console.log("Loaded viewer successfully with given asset...");
}

function loadViewErrorFunc()
{
    console.log("ERROR: could not load asset into viewer...");
}

    // load a particular viewable into the viewer
function loadView(path, dbpath) {
    console.log("Loading view file: " + path);
        
        // TEST:  trying out loading only specific objects instead of the whole model.  Almost works, but needs
        // a little bit of polish.
    //var idArray = [1023, 1018, 1898];   // hardwire 3 walls of default revit file
    //_viewer.loadModel(path, idArray, DbPath, loadViewSuccessFunc, loadViewErrorFunc);
    
    _viewer.load(path, dbpath, loadViewSuccessFunc, loadViewErrorFunc);
}

    // wrap this in a simple function so we can pass it into the Initializer options object
function getAccessToken() {
    //return _myAuthToken.value();
	return "";
}

    // called when HTML page is finished loading, trigger loading of default model into viewer
function loadInitialModel(path, dbpath) {
    //dbgPrintLmvVersion();
    
    var options = {};
    options.env = _viewerEnv;                // Local
    options.getAccessToken = getAccessToken;
    options.refreshToken   = getAccessToken;
    
    Teratek.Viewing.Initializer(options, function() {
        initializeViewer();
        // load svf here
		loadView(path, dbpath);
    });
}
