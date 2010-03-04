package com.synitex.email.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.google.appengine.api.datastore.Blob;

public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		ServletFileUpload upload = new ServletFileUpload();
		resp.setContentType("text/plain");
		PrintWriter out = resp.getWriter();
		try {

			FileItemIterator fileIterator = upload.getItemIterator(req);
			while (fileIterator.hasNext()) {
				FileItemStream item = fileIterator.next();
				InputStream in = null;
				try {
					in = item.openStream();
					if (!item.isFormField()) {
						byte[] content = IOUtils.toByteArray(in);

						ImageItem fileItem = new ImageItem();
						fileItem.setData(new Blob(content));
						fileItem = pm.makePersistent(fileItem);

						Long id = fileItem.getId();
						out.print("id=" + id);

					}
				} finally {
					if (in != null) {
						IOUtils.closeQuietly(in);
					}
				}
			}

			out.flush();

		} catch (FileUploadException e) {
			throw new ServletException(e);
		}

	}

}
