package com.synitex.email.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

public class EmbeddedImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

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

						byte[] base64 = Base64.encodeBase64(content);
						String str = new String(base64);
						// System.out.println("base64:" + str);
						String ret = "data:image/png;base64," + str;
						out.print("image=" + ret);

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
