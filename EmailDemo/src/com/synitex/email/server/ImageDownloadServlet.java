package com.synitex.email.server;

import java.io.BufferedOutputStream;
import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Blob;

public class ImageDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		process(req, resp);
	}

	protected void doPost(javax.servlet.http.HttpServletRequest req,
			javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		process(req, resp);
	};

	private void process(javax.servlet.http.HttpServletRequest req,
			javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {

		PersistenceManager pm = PMF.get().getPersistenceManager();
		String id = req.getParameter("id");

		resp.setContentType("image/jpeg");
		BufferedOutputStream out = new BufferedOutputStream(resp
				.getOutputStream());

		if (id != null && !"".equals(id)) {
			Long key = Long.valueOf(id);
			ImageItem imageItem = pm.getObjectById(ImageItem.class, key);
			Blob blob = imageItem.getData();
			out.write(blob.getBytes());
		} else {
			out.write("error".getBytes());
		}
		out.flush();

	}

}
