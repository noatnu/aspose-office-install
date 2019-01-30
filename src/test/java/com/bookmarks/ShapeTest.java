package com.bookmarks;

import com.aspose.words.*;
import com.help.TestFile;
import org.testng.annotations.Test;

/**
 * @Auther: zch
 * @Date: 2019/1/30 15:16
 * @Description:
 */
public class ShapeTest {

    /**
     * 获取所有的图像类型
     * @throws Exception
     */
    @Test
    public void getAllShape()throws Exception{
        String dataDir = TestFile.getTestDataParentDir(this.getClass());
        Document doc = new Document(dataDir+"Image.SampleImages.doc");

        NodeCollection shapes = doc.getChildNodes(NodeType.SHAPE, true);
        int imageIndex = 0;
        for (Shape shape : (Iterable<Shape>) shapes)
        {
            if (shape.hasImage())
            {
                String imageFileName = java.text.MessageFormat.format(
                        "Image.ExportImages.{0} Out{1}", imageIndex, FileFormatUtil.imageTypeToExtension(shape.getImageData().getImageType()));
                shape.getImageData().save(dataDir + imageFileName);
                imageIndex++;
            }
        }
    }

    /**
     * 包含常量的实用程序类。指定Microsoft Word文档中的形状类型。如默认图片,三角形，圆形等
     * @throws Exception
     */
    @Test
    public void test4()throws Exception{
        String dataDir = TestFile.getTestDataParentDir(this.getClass());
        Document doc = new Document();

        Shape shape = new Shape(doc, ShapeType.PLUS);
        shape.getImageData().setImage(dataDir + "Test.png");
        shape.setWidth(100);
        shape.setHeight(100);

        doc.getFirstSection().getBody().getFirstParagraph().appendChild(shape);

        doc.save(dataDir + "output.doc");
    }

    @Test
    public void test5()throws Exception{
        String dataDir = TestFile.getTestDataParentDir(this.getClass());
        // This creates a builder and also an empty document inside the builder.
        DocumentBuilder builder = new DocumentBuilder();

        // By default, the image is inline.
        Shape shape = builder.insertImage(dataDir + "Images\\Aspose.Words.gif");

        // Make the image float, put it behind text and center on the page.
        shape.setWrapType(WrapType.NONE);
        shape.setBehindText(true);
        shape.setRelativeHorizontalPosition(RelativeHorizontalPosition.PAGE);
        shape.setHorizontalAlignment(HorizontalAlignment.CENTER);
        shape.setRelativeVerticalPosition(RelativeVerticalPosition.PAGE);
        shape.setVerticalAlignment(VerticalAlignment.CENTER);

        builder.getDocument().save(dataDir + "output.doc");
    }

    /**
     * 书签替换图形
     * @throws Exception
     */
    @Test
    public void test3()throws Exception{
        String dataDir = TestFile.getTestDataParentDir(this.getClass());
        Document doc = new Document(dataDir + "MyBookmark.docx");
        DocumentBuilder builder = new DocumentBuilder(doc);
        Shape shape = builder.insertImage(dataDir + "Test.png");
        shape.setWidth(40);
        shape.setHeight(40);

        shape.setWrapType(WrapType.NONE);
//        shape.setBehindText(true);//指定形状是在文本下方还是上方。仅对顶级形状有效。默认值为假。
        builder.moveToBookmark("ntf010145060");
        builder.insertNode(shape);
        doc.save(dataDir + "output.doc");
    }

    /**
     * 显示如何在页面中间插入浮动图像。
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        String dataDir = TestFile.getTestDataParentDir(this.getClass());
        // This creates a builder and also an empty document inside the builder.
        DocumentBuilder builder = new DocumentBuilder();

        // By default, the image is inline.
        Shape shape = builder.insertImage(dataDir + "Images\\Aspose.Words.gif");

        // Make the image float, put it behind text and center on the page.
        shape.setWrapType(WrapType.NONE);
        shape.setBehindText(true);
        shape.setRelativeHorizontalPosition(RelativeHorizontalPosition.PAGE);
        shape.setHorizontalAlignment(HorizontalAlignment.CENTER);
        shape.setRelativeVerticalPosition(RelativeVerticalPosition.PAGE);
        shape.setVerticalAlignment(VerticalAlignment.CENTER);

        builder.getDocument().save(dataDir + "Image.CreateFloatingPageCenterOut.doc");
    }

    @Test
    public void test() throws Exception {
        //ExStart:DocumentBuilderSetImageAspectRatioLocked
        // The path to the documents directory.
        String dataDir = TestFile.getTestDataParentDir(this.getClass());

        // Open the document.
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        Shape shape = builder.insertImage(dataDir + "Test.png");
//        shape.setAspectRatioLocked(false);
        shape.setAnchorLocked(false);

        doc.save(dataDir + "output.doc");
        //ExEnd:DocumentBuilderSetImageAspectRatioLocked
    }

    /**
     *
     * 表示绘图层中的对象，例如自选图形、文本框、自由形式、OLE对象、ActiveX控件或图片。
     使用形状类，可以在Microsoft Word文档中创建或修改形状。
     形状的一个重要属性是它的shapeType。不同类型的形状在Word文档中具有不同的功能。例如，只有图像和OLE形状可以在其中包含图像。大多数形状都可以有文本，但不是全部。
     形状可以包含文本，也可以包含段落和表节点作为子级。
     * */

}
