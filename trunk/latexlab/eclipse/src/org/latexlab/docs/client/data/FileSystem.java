package org.latexlab.docs.client.data;

import com.google.gwt.accounts.client.User;
import com.google.gwt.gdata.ext.client.documents.DocumentContents;
import com.google.gwt.gdata.ext.client.documents.DocumentContentsCallback;
import com.google.gwt.gdata.ext.client.documents.DocumentEntry;
import com.google.gwt.gdata.ext.client.documents.DocumentEntryCallback;
import com.google.gwt.gdata.ext.client.documents.DocumentFeed;
import com.google.gwt.gdata.ext.client.documents.DocumentFeedCallback;
import com.google.gwt.gdata.ext.client.documents.DocumentsService;
import com.google.gwt.gdata.client.GDataRequestParameters;
import com.google.gwt.gdata.client.atom.Category;
import com.google.gwt.gdata.client.atom.Text;
import com.google.gwt.gdata.client.impl.CallErrorException;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.latexlab.docs.client.data.FileSystemEntry.FileSystemEntryComparator;

public class FileSystem{
  
  private static final String APPLICATION_IDENTIFIER = "LaTeX-Lab";
  private static final String DOCS_SCOPE = "https://docs.google.com/feeds/";
  
  private static final DocumentsService service =
    DocumentsService.newInstance(APPLICATION_IDENTIFIER);
  
  private static ArrayList<FileSystemEntry> entries;
  private FileSystem() { }
  
  public static boolean login() {
    switch (User.getStatus(DOCS_SCOPE)) {
      case LOGGED_OUT:
        User.login(DOCS_SCOPE);
      case LOGGED_IN:
    	return true;
    }
    return false;
  }
  
  public static void logout(Runnable callback) {
    switch (User.getStatus(DOCS_SCOPE)) {
      case LOGGED_IN:
        User.logout(callback);
        break;
    }
  }
  
  public static String getAuthenticationToken() {
	  return User.checkLogin(DOCS_SCOPE);
  }
  
  public static void deleteDocument(final String id,
      final AsyncCallback<FileSystemEntry> callback) {
    GDataRequestParameters pars = GDataRequestParameters.newInstance();
    pars.setEtag("*");
    final String uri = 
    	DOCS_SCOPE + "documents/private/full/document%3A" + id;
    service.deleteDocumentEntry(uri, new DocumentEntryCallback() {
      public void onFailure(CallErrorException caught) {
        callback.onFailure(new Throwable(caught.getMessage()));
      }
      public void onSuccess(DocumentEntry result) {
        FileSystemEntry entry = null;
        if (result != null) {
          entry = FileSystemEntry.fromGDataEntry(result);
        }
        callback.onSuccess(entry);
        entries = null;
      }
    }, pars);
  }
  
  public static void getEntries(AsyncCallback<ArrayList<FileSystemEntry>> callback) {
    if (entries == null) {
      loadEntries(callback);
    } else {
      callback.onSuccess(entries);
    }
  }
  
  public static void getEntries(final String folder, final AsyncCallback<ArrayList<FileSystemEntry>> callback) {
	AsyncCallback<ArrayList<FileSystemEntry>> ncb =
		new AsyncCallback<ArrayList<FileSystemEntry>>() {
          @Override
		  public void onFailure(Throwable caught) {
            callback.onFailure(caught);
		  }
          @Override
          public void onSuccess(ArrayList<FileSystemEntry> result) {
        	ArrayList<FileSystemEntry> entries = new ArrayList<FileSystemEntry>();
        	for (FileSystemEntry entry : result) {
              if ((entry.getParent() == null && entry.getParent() == folder) ||
                  (entry.getParent() != null && entry.getParent().equals(folder))) {
                entries.add(entry);
              }
        	}
            callback.onSuccess(entries);
		  }
	};
    if (entries == null) {
      loadEntries(ncb);
    } else {
      ncb.onSuccess(entries);
    }
  }
  
  public static void loadEntries(
      final AsyncCallback<ArrayList<FileSystemEntry>> callback) {
    service.getDocumentFeed(
    	DOCS_SCOPE + "documents/private/full?showfolders=true",
        new DocumentFeedCallback() {
      public void onFailure(CallErrorException caught) {
        callback.onFailure(new Throwable(caught.getMessage()));
      }
      public void onSuccess(DocumentFeed result) {
        entries = new ArrayList<FileSystemEntry>();
        for (DocumentEntry doc : result.getEntries()) {
          entries.add(FileSystemEntry.fromGDataEntry(doc));
        }
        Collections.sort(entries, new FileSystemEntryComparator());
        callback.onSuccess(entries);
      }
    });
  }
  
  public static void getEntry(String id,
      final AsyncCallback<FileSystemEntry> callback) {
    for (FileSystemEntry entry : entries) {
      if (entry.getId().equals(id)) {
        callback.onSuccess(entry);
        return;
      }
    }
    loadEntry(id, callback);
  }
  
  public static void loadEntry(String id,
      final AsyncCallback<FileSystemEntry> callback) {
    service.getDocumentEntry(
    	DOCS_SCOPE + "documents/private/full/document%3A" +
        id, new DocumentEntryCallback() {
      public void onFailure(CallErrorException caught) {
        callback.onFailure(new Throwable(caught.getMessage()));
      }
      public void onSuccess(DocumentEntry result) {
        callback.onSuccess(FileSystemEntry.fromGDataEntry(result));
      }
    });
  }
  
  public static void loadDesktopEntries(
      final AsyncCallback<ArrayList<FileSystemEntry>> callback) {
    service.getDocumentFeed(
        DOCS_SCOPE + "documents/private/full?showfolders=false&q=cloudie&r=" + (new Date()).getTime(),
        new DocumentFeedCallback() {
      public void onFailure(CallErrorException caught) {
        callback.onFailure(new Throwable(caught.getMessage()));
      }
      public void onSuccess(DocumentFeed result) {
        ArrayList<FileSystemEntry> entries = new ArrayList<FileSystemEntry>();
        for (DocumentEntry doc : result.getEntries()) {
          entries.add(FileSystemEntry.fromGDataEntry(doc));
        }
        Collections.sort(entries, new FileSystemEntryComparator());
        callback.onSuccess(entries);
      }
    });
  }
  
  public static void loadDocumentContents(String id,
      final AsyncCallback<String> callback) {
    service.getDocumentContents(id, new DocumentContentsCallback() {
      public void onFailure(CallErrorException caught) {
        callback.onFailure(new Throwable(caught.getMessage()));
      }
      public void onSuccess(DocumentContents result) {
        callback.onSuccess(result.getText());
      }
    });
  }

  public static void saveDocument(final String documentId, String title,
      String contents, final AsyncCallback<FileSystemEntry> callback) {
    if (documentId == null || documentId.equals("")) {
      createDocument(title, contents, new AsyncCallback<FileSystemEntry>() {
        public void onFailure(Throwable caught) {
          callback.onFailure(caught);
        }
        public void onSuccess(FileSystemEntry result) {
          callback.onSuccess(result);
        }
      });
    } else {
      setDocumentContents(documentId, contents, new AsyncCallback<String>() {
        public void onFailure(Throwable caught) {
          callback.onFailure(caught);
        }
        public void onSuccess(String result) {
          loadEntry(documentId, new AsyncCallback<FileSystemEntry>() {
            public void onFailure(Throwable caught) {
              callback.onFailure(caught);
            }
            public void onSuccess(FileSystemEntry result) {
              callback.onSuccess(result);
            }
          });
        }
      });
    }
  }
  
  public static void setDocumentContents(String id, String contents,
      final AsyncCallback<String> callback) {
    GDataRequestParameters pars = GDataRequestParameters.newInstance();
    pars.setEtag("*");
    service.setDocumentContents(id, contents, new DocumentContentsCallback() {
      public void onFailure(CallErrorException caught) {
        callback.onFailure(new Throwable(caught.getMessage()));
      }
      public void onSuccess(DocumentContents result) {
        callback.onSuccess(result.getText());
      }
    }, pars);
  }
  
  public static void setDocumentStarred(String id, final boolean starred,
      final AsyncCallback<FileSystemEntry> callback) {
    final String uri = 
    	DOCS_SCOPE + "documents/private/full/document%3A" + id;
    service.getDocumentEntry(uri, new DocumentEntryCallback() {
      public void onFailure(CallErrorException caught) {
        callback.onFailure(new Throwable(caught.getMessage()));
      }
      public void onSuccess(DocumentEntry result) {
        ArrayList<Category> cats = new ArrayList<Category>();
        boolean needStar = starred;
        for (Category cat : result.getCategories()) {
          if (cat.getTerm().equals("http://schemas.google.com/g/2005/labels#starred")) {
            if (starred) {
              cats.add(cat);
              needStar = false;
            }
          } else {
            cats.add(cat);
          }
        }
        if (needStar) {
          Category cat = Category.newInstance();
          cat.setScheme("http://schemas.google.com/g/2005/labels");
          cat.setTerm("http://schemas.google.com/g/2005/labels#starred");
          cat.setLabel("starred");
          cats.add(cat);
        }
        result.setCategories(cats.toArray(new Category[cats.size()]));
        GDataRequestParameters pars = GDataRequestParameters.newInstance();
        pars.setEtag("*");
        service.updateDocumentEntry(uri, result, new DocumentEntryCallback() {
          public void onFailure(CallErrorException caught) {
            callback.onFailure(new Throwable(caught.getMessage()));
          }
          public void onSuccess(DocumentEntry result) {
            callback.onSuccess(FileSystemEntry.fromGDataEntry(result));
          }
        }, pars);
      }
    });
  }
  
  public static void setDocumentName(String id, final String newName,
      final AsyncCallback<FileSystemEntry> callback) {
    final String uri = 
    	DOCS_SCOPE + "documents/private/full/document%3A" + id;
    service.getDocumentEntry(uri, new DocumentEntryCallback() {
      public void onFailure(CallErrorException caught) {
        callback.onFailure(new Throwable(caught.getMessage()));
      }
      public void onSuccess(DocumentEntry result) {
        result.getTitle().setText(newName);
        GDataRequestParameters pars = GDataRequestParameters.newInstance();
        pars.setEtag("*");
        service.updateDocumentEntry(uri, result, new DocumentEntryCallback() {
          public void onFailure(CallErrorException caught) {
            callback.onFailure(new Throwable(caught.getMessage()));
          }
          public void onSuccess(DocumentEntry result) {
            callback.onSuccess(FileSystemEntry.fromGDataEntry(result));
          }
        }, pars);
      }
    });
  }
  
  public static void createDocument(String title, final String contents,
      final AsyncCallback<FileSystemEntry> callback) {
    DocumentEntry entry = DocumentEntry.newInstance();
    entry.setTitle(Text.newInstance());
    entry.getTitle().setText(title);
    Category documentCategory = Category.newInstance();
    documentCategory.setScheme("http://schemas.google.com/g/2005#kind");
    documentCategory.setLabel("document");
    documentCategory.setTerm("http://schemas.google.com/docs/2007#document");
    entry.setCategories(new Category[] { documentCategory });
    service.insertDocumentEntry(DOCS_SCOPE + "documents/private/full", entry, new DocumentEntryCallback() {
      public void onFailure(CallErrorException caught) {
        callback.onFailure(new Throwable(caught.getMessage()));
      }
      public void onSuccess(DocumentEntry result) {
        entries = null;
        final DocumentEntry doc = result;
        GDataRequestParameters pars = GDataRequestParameters.newInstance();
        pars.setEtag("*");
        service.setDocumentContents(result.getResourceId().getValue(), contents,
            new DocumentContentsCallback(){
              public void onFailure(CallErrorException caught) {
                callback.onFailure(new Throwable(caught.getMessage()));
              }
              public void onSuccess(DocumentContents result) {
                callback.onSuccess(FileSystemEntry.fromGDataEntry(doc));
              }
        }, pars);
      }
    });
  }
  
}
