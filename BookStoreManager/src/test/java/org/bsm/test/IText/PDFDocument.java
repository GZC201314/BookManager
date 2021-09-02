package org.bsm.test.IText;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import org.junit.Test;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

public class PDFDocument {
    private static final String ORIG = "src/main/resources/image/myplot.png";
    private static final String OUTPUT_FOLDER = "./";

    @Test
    public void pdfTest() throws MalformedURLException, FileNotFoundException {
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(OUTPUT_FOLDER + "ImageToPdf.pdf"));
        Document document = new Document(pdfDocument);
        ImageData imageData = ImageDataFactory.create(ORIG);
        Image image = new Image(imageData);
        image.setWidth(pdfDocument.getDefaultPageSize().getWidth() - 50);
        image.setAutoScaleHeight(true);
        document.add(image);
        pdfDocument.close();
    }
}
