package org.latexlab.clsi.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * CLSI service.
 */
public class ClsiService {

  private static final ClsiServiceProxyAsync serviceProxy =
    GWT.create(ClsiServiceProxy.class);
  
  private int instance = 0;
  private String serviceUrl, asyncPath, token, id;
  private ScriptElement link;
  private Timer timer;
  private AsyncCallback<ClsiServiceCompileResponse> handler;
  
  public ClsiService() {
    initialize(this);
  }
  
  public ClsiService(String serviceUrl, String asyncPath, String token) {
	this.serviceUrl = serviceUrl;
	this.asyncPath = asyncPath;
	this.token = token;
    initialize(this);
  }

  public String getServiceUrl() {
    return serviceUrl;
  }

  public void setServiceUrl(String serviceUrl) {
    this.serviceUrl = serviceUrl;
  }

  public String getAsyncPath() {
    return asyncPath;
  }

  public void setAsyncPath(String asyncPath) {
    this.asyncPath = asyncPath;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public void compile(String name, String contents, String authToken, final String id,
		    ResourceReference[] resources, String compiler, String format, AsyncCallback<ClsiServiceCompileResponse> callback) {
	instance++;
	handler = callback;
    serviceProxy.compile(name, contents, authToken, serviceUrl, token, id, String.valueOf(instance), resources, compiler, format,
      new AsyncCallback<Boolean>() {
		@Override
		public void onFailure(Throwable caught) {
		  handler.onFailure(caught);
		}
		@Override
		public void onSuccess(Boolean result) {
		  detectResponse(id, instance);
		}
    });
  }
		
  public void getCurrentUser(AsyncCallback<String> callback) {
	serviceProxy.getCurrentUser(callback);
  }
  
  private void detectResponse(String id, int instance) {
	this.id = id;
	if (timer != null) {
	  timer.cancel();
	}
    timer = new Timer() {
      public void run() {
        checkResponse();
      }
    };
    timer.scheduleRepeating(1000);
  }
  
  private void checkResponse() {
	if (!asyncPath.endsWith("/")) {
	  asyncPath += "/";
	}
	String src = asyncPath + token + "/" + id + "/response.js?r=" + (new Date()).getTime();
	if (link != null) {
	  link.removeFromParent();
	}
    Document doc = Document.get();
    link = doc.createScriptElement();
    link.setSrc(src);
    link.setType("text/javascript");
    doc.getBody().appendChild(link);
  }
  
  private static final native void initialize(ClsiService obj) /*-{
  	$wnd.clsi_oncompile = function(r) {
  	  obj.@org.latexlab.clsi.client.ClsiService::onResponse(Lorg/latexlab/clsi/client/ClsiServiceCompileResponse;)(r);
  	}
  }-*/;
  
  public void onResponse(ClsiServiceCompileResponse resp) {
    if (timer != null) {
      timer.cancel();
    }
    if (handler != null) {
      AsyncCallback<ClsiServiceCompileResponse> cb = handler;
      handler = null;
      cb.onSuccess(resp);
    }
  }
  
}