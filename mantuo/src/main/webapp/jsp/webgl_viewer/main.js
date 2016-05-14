
var auth = null;
var env;
var VIEWING_URL;
var SEARCH_URL;
var RESOURCE_ROOT = "";

// Make sure only one of these is set to true.
var USE_OAUTH_NONE = true;
var USE_OAUTH_ZERO = false;
var USE_OAUTH_ONE = false;
var USE_OAUTH_TWO = false;
// Control whether or not viewer should interactive with viewing service set token end point.
// For prod release, this must set to false, since hosting application will set token instead.
// For development / debugging, set this to true so viewer will take care of set token in cookie.
var SHOULD_SET_TOKEN = true;

// Environment configurations for various services end points.
// Please use this instead of hard code service end points in your code.
var EnvironmentConfigurations = {
    Local : {
        RESOURCE : '',
        VIEWING : '',
        SEARCH : ''
    },
    Development : {
        RESOURCE : '',
        VIEWING : '',
        SEARCH : ''
    },
    Staging : {
        RESOURCE : '',
        VIEWING : '',
        SEARCH : ''
    },
    Production : {
        RESOURCE : '',
        VIEWING : '',
        SEARCH : ''
    }
}

function setAuthTokenInCookie(accessToken, onSuccessCallback) {
    var token =
    {
        "oauth":
        {
            "token":""
        }
    };
    token.oauth.token = accessToken;

    console.log("auth token : " + JSON.stringify(token));

    var xhr = new XMLHttpRequest();
    xhr.open("POST", VIEWING_URL + "/token", true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.withCredentials = true;

    if(onSuccessCallback) {
        xhr.onload = onSuccessCallback;
    }

    xhr.send(JSON.stringify(token));
}

function initializeEnvironmentVariable(options) {
    if (options && options.env) {
        env = options.env;
    } else {
        switch (window.location.hostname) {
            case "" :
                env = 'Development';
                break;
            case "" :
                env = 'Staging';
                break;
            case "" :
                env = 'Production';
                break;
            default:
                env = 'Local';
        }
    }

    console.log("Host name : " + window.location.hostname);
    console.log("Environment initialized as : " + env);
}

function initializeServiceEndPoints() {
    VIEWING_URL = EnvironmentConfigurations[env]['VIEWING'];
    SEARCH_URL = EnvironmentConfigurations[env]['SEARCH'];
    RESOURCE_ROOT = EnvironmentConfigurations[env]['RESOURCE'];
}

// Returns the query parameter value from window url
function getParameterByName( name ) {
    return getParameterByNameFromPath(name,window.location.href);
}

// return value of parameter from a url
function getParameterByNameFromPath(name,url) {
    name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
    var regexS = "[\\?&]"+name+"=([^&#]*)";
    var regex = new RegExp( regexS );
    var results = regex.exec(url);
    if( results == null )
        return "";
    else
        return decodeURIComponent(results[1].replace(/\+/g, " "));
}

// Return a list of values of query parameters with same name.
// e.g. http://foo?bar=0&bar=1&bar=2 would return [0,1,2] for 'bar'.
function getQueryParameters(name, url){
    var values = [];
    if(!url)
        url = window.location.href;
    name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
    var pattern = name + '=([^&#]+)';
    var reg = new RegExp(pattern,'ig');
    while(true){
        var matches = reg.exec(url);
        if(matches && matches[1]){
            values.push(matches[1]);
        }
        else
            break;
    }

    if(!values.length)
        return null;
    else
        return values.length == 1 ? values[0] : values;
}

// Initialize authentication related stuff.
function initializeAuth(onSuccessCallback, options) {
    auth = new Auth();
    if (USE_OAUTH_ZERO) {
        var accessToken = getParameterByName("accessToken");
        if (!accessToken) accessToken = "fRK+hYtOoq0+Y1xL1i8I/jeCy+o=";
        auth.configureOAuthZero(accessToken);
    } else if (USE_OAUTH_ONE) {
        var consumerKey = getParameterByName("consumerKey");
        var consumerSecret = getParameterByName("consumerSecret");
        var token = getParameterByName("token");
        var tokenSecret = getParameterByName("tokenSecret");

        if (!consumerKey) consumerKey = "648b3663-2571-42f2-b6f9-f0b90e198222";
        if (!consumerSecret) consumerSecret = "cd1213d8-b519-4a45-94ad-2b309d699b12";
        if (!token) token = "";
        if (!tokenSecret) tokenSecret = "";

        auth.configureOAuthOne(consumerKey, consumerSecret, token, tokenSecret);
    } else if (USE_OAUTH_TWO) {
        auth.configureOAuthTwo();
    } else if (USE_OAUTH_NONE) {
        auth.configureOAuthNone();

        var accessToken;
        if (options) {
            if (options.getAccessToken) {
                accessToken = options.getAccessToken();
            } else if (options.accessToken) {
                accessToken = options.accessToken;
            } else {
                accessToken = getParameterByName("accessToken");
            }
        } else {
            accessToken = getParameterByName("accessToken");
        }

        if (!accessToken) {
            accessToken = "IL2Ezzvxg/Xo60Jqp5n4SWfZJoc=";
            console.log("Warning : no access token is provided. Use built in token : " + accessToken);
        }

        // For testing only. This should be moved to hosting application,
        // so the access token can be securely passed to viewer through
        // Set-Cookie header of viewing service set token API response.
        if (SHOULD_SET_TOKEN) {
            setAuthTokenInCookie(accessToken, onSuccessCallback);
        }
    }

    return auth;
}

// A single function that initialize everything.
function initializeAll(callback, options) {
    initializeEnvironmentVariable(options);
    initializeServiceEndPoints();
    initializeAuth(callback, options);
}

function stderr(msg) {
    if (ENABLE_TRACE) {
        console.log(msg);
    }
}