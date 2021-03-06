package com.test.document;

import com.aspose.words.*;
import com.aspose.words.Shape;
import com.help.Utils;
import org.testng.annotations.Test;

import java.awt.*;


/**
 * @Auther: zch
 * @Date: 2019/1/11 11:15
 * @Description:在文档中插入水印
 */
public class AddWaterMark {

    /**
     * 在文档中插入水印
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        String dataDir = Utils.getDataDir(this.getClass());

        Document doc = new Document(dataDir + "document.doc");
        insertWatermarkText(doc, "CONFIDENTIAL");
        doc.save(dataDir + "AsposeWatermark.doc");

        System.out.println("\nProcess Completed Successfully");
    }

    /**
     * Inserts a watermark into a document.(在文档中插入水印)
     *
     * @param doc           The input document.
     * @param watermarkText Text of the watermark.
     */
    private void insertWatermarkText(Document doc, String watermarkText) throws Exception {
        // Create a watermark shape. This will be a WordArt shape.
        // You are free to try other shape types as watermarks.
        Shape watermark = new Shape(doc, ShapeType.TEXT_PLAIN_TEXT);

        // Set up the text of the watermark.
        watermark.getTextPath().setText(watermarkText);
        watermark.getTextPath().setFontFamily("Arial");
        watermark.setWidth(500);
        watermark.setHeight(100);
        // Text will be directed from the bottom-left to the top-right corner.
        watermark.setRotation(-40);
        // Remove the following two lines if you need a solid black text.
        watermark.getFill().setColor(Color.GRAY); // Try LightGray to get more Word-style watermark
        watermark.setStrokeColor(Color.GRAY); // Try LightGray to get more Word-style watermark

        // Place the watermark in the page center.
        watermark.setRelativeHorizontalPosition(RelativeHorizontalPosition.PAGE);
        watermark.setRelativeVerticalPosition(RelativeVerticalPosition.PAGE);
        watermark.setWrapType(WrapType.NONE);
        watermark.setVerticalAlignment(VerticalAlignment.CENTER);
        watermark.setHorizontalAlignment(HorizontalAlignment.CENTER);

        // Create a new paragraph and append the watermark to this paragraph.
        Paragraph watermarkPara = new Paragraph(doc);
        watermarkPara.appendChild(watermark);

        // Insert the watermark into all headers of each document section.
        for (Section sect : doc.getSections()) {
            // There could be up to three different headers in each section, since we want
            // the watermark to appear on all pages, insert into all headers.
            insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_PRIMARY);
            // insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_FIRST);
            // insertWatermarkIntoHeader(watermarkPara, sect, HeaderFooterType.HEADER_EVEN);
        }
    }

    private void insertWatermarkIntoHeader(Paragraph watermarkPara, Section sect, int headerType) throws Exception {
        HeaderFooter header = sect.getHeadersFooters().getByHeaderFooterType(headerType);

        if (header == null) {
            // There is no header of the specified type in the current section, create it.
            header = new HeaderFooter(sect.getDocument(), headerType);
            sect.getHeadersFooters().add(header);
        }

        // Insert a clone of the watermark into the header.
        header.appendChild(watermarkPara.deepClone(true));
    }

}
