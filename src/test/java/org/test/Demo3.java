package org.test;

import com.aspose.words.Bookmark;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.help.TestFile;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author noatn
 * @Description
 * @createDate 2019/1/16
 **/
public class Demo3 {

    final String dataPath = TestFile.getTestDataParentDir(this.getClass());

    /**
     * 书签删除
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        //https://bbs.csdn.net/topics/310190259

        //https://blog.csdn.net/u010117782/article/details/84884076

        Map<String, String> map = Maps.newHashMap();
        map.put("文号", "测试1号!");
        replaceBookmark(String.format("%s%s", dataPath, "房产预评报告模板.doc"), map,true);
    }

    private void replaceBookmark(String filePath, Map<String, String> map) throws Exception {
        // Map<String, String> map == > 书签名称,需要替换的内容
        if (StringUtils.isBlank(filePath))
            throw new Exception("error: empty file path");
        if (map == null || map.isEmpty())
            throw new Exception("error: empty map");
        Document doc = new Document(filePath);
        DocumentBuilder builder = new DocumentBuilder(doc);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {

//            builder.moveToBookmark(stringStringEntry.getKey());
//            builder.write(stringStringEntry.getValue());



            Bookmark bookmark = doc.getRange().getBookmarks().get(stringStringEntry.getKey());
            bookmark.setText(stringStringEntry.getValue());
            //删除书签
//            doc.getRange().getBookmarks().remove(bookmark);
        }
        doc.save(String.format("%s%s",dataPath, UUID.randomUUID().toString()+".doc"));
    }

    /**
     * Map<String, String> map == > 书签名称,需要替换的内容
     * @param filePath 具体文件路径
     * @param map 书签名称,需要替换的内容
     * @param deleteBookMark 是否需要在替换完成时删除书签
     * @throws Exception
     */
    private   void replaceBookmark(String filePath, Map<String, String> map, boolean deleteBookMark) throws Exception {
        List<String> bookmarkList = Lists.newArrayList();
        if (StringUtils.isBlank(filePath)) {
            throw new Exception("error: empty file path");
        }
        if (map == null || map.isEmpty()) {
            throw new Exception("error: empty map");
        }
        Document doc = new Document(filePath);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            Bookmark bookmark = doc.getRange().getBookmarks().get(stringStringEntry.getKey());
            bookmark.setText(stringStringEntry.getValue());
            bookmarkList.add(bookmark.getName());
        }
        String dataPathA = String.format("%s%s",dataPath, UUID.randomUUID().toString()+".doc") ;
        doc.save(dataPathA);
        if (deleteBookMark) {
            Document docDelete = new Document(dataPathA);
            if (CollectionUtils.isNotEmpty(bookmarkList)) {
                for (String bookmark : bookmarkList) {
                    //删除书签
                    docDelete.getRange().getBookmarks().remove(bookmark);
                }
            }
            docDelete.save(dataPathA);
        }
    }

}
