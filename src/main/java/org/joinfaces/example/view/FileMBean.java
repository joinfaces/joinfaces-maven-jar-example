package org.joinfaces.example.view;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

@Named
@ViewScoped
public class FileMBean implements Serializable {
	
	private UploadedFile uploadedFile;

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile file) {
		this.uploadedFile = file;
	}	
	
	private StreamedContent downloadFile;

	public StreamedContent getDownloadFile() {
		return downloadFile;
	}
	
	public void upload() throws IOException {
        if(uploadedFile != null) {
			downloadFile = new DefaultStreamedContent(uploadedFile.getInputstream()
				, uploadedFile.getContentType(), uploadedFile.getFileName());
        }
    }
}
