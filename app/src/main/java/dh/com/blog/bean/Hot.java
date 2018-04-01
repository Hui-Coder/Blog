package dh.com.blog.bean;

/**
 * Created by King on 2018/3/18.
 */

public class Hot {

    /**
     * summary : 作者：陈皓来源：极客时间专栏春节假期就要结束了，现在的你或许正在归程中，或许在默默梳理新一年的计划，摩拳擦掌，希望明年此时技术更进一步，收入也更可观。作为一名程序员，也许应该感到庆幸，因为写程序是一门&ldquo;手艺活儿&rdquo;，除了拿固定的工资，你还可以用技术变现。...
     * comments : 15
     * link : {"rel":"alternate","href":"http://news.cnblogs.com/n/591654/"}
     * topicIcon : http://images0.cnblogs.com/news_topic///images0.cnblogs.com/news_topic/programmer.jpg
     * id : 591654
     * published : 2018-03-14T22:00:00+08:00
     * title : 程序员增加收入实用指南
     * updated : 2018-03-19T07:27:18Z
     * views : 3836
     */

    private EntryBean entry;

    public EntryBean getEntry() {
        return entry;
    }

    public void setEntry(EntryBean entry) {
        this.entry = entry;
    }

    public static class EntryBean {
        private String summary;
        private int comments;
        private String topicIcon;
        private int id;
        private String published;
        private String title;
        private String updated;
        private int views;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public String getTopicIcon() {
            return topicIcon;
        }

        public void setTopicIcon(String topicIcon) {
            this.topicIcon = topicIcon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPublished() {
            return published;
        }

        public void setPublished(String published) {
            this.published = published;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        @Override
        public String toString() {
            return "EntryBean{" +
                    "summary='" + summary + '\'' +
                    ", comments=" + comments +
                    ", topicIcon='" + topicIcon + '\'' +
                    ", id=" + id +
                    ", published='" + published + '\'' +
                    ", title='" + title + '\'' +
                    ", updated='" + updated + '\'' +
                    ", views=" + views +
                    '}';
        }
    }
}
