package com.ka.krishnaaqua.data;

public class HistoryData {
//    private String title;
//    private String rating;
//    private String year;
//    private String plot;
//    private boolean expanded;
//
//    public HistoryData ( String title , String rating , String year , String plot ) {
//        this.title    = title;
//        this.rating   = rating;
//        this.year     = year;
//        this.plot     = plot;
//     }
//
//
//    public String getTitle () {
//        return title;
//    }
//
//    public void setTitle ( String title ) {
//        this.title = title;
//    }
//
//    public String getRating () {
//        return rating;
//    }
//
//    public void setRating ( String rating ) {
//        this.rating = rating;
//    }
//
//    public String getYear () {
//        return year;
//    }
//
//    public void setYear ( String year ) {
//        this.year = year;
//    }
//
//    public String getPlot () {
//        return plot;
//    }
//
//    public void setPlot ( String plot ) {
//        this.plot = plot;
//    }
//
//    public boolean isExpanded () {
//        return expanded;
//    }
//
//
//    public void setExpanded ( boolean expanded ) {
//        this.expanded = expanded;
//    }
//
//    @Override
//    public String toString () {
//        return "History{" +
//                "title='" + title + '\'' +
//                ", rating='" + rating + '\'' +
//                ", year='" + year + '\'' +
//                ", plot='" + plot + '\'' +
//                ", expanded=" + expanded +
//                '}';
//    }
    private int id;
    private int u_id;
    private String startDate;
    private String endDate;
    private int total;
    private int qty;
    private String paymentId;
    private boolean expanded;

    public HistoryData ( int id , int u_id , String startDate , String endDate , int total , int qty , String paymentId ) {
        this.id        = id;
        this.u_id      = u_id;
        this.startDate = startDate;
        this.endDate   = endDate;
        this.total     = total;
        this.qty       = qty;
        this.paymentId = paymentId;
    }

    public int getId () {
        return id;
    }

    public void setId ( int id ) {
        this.id = id;
    }

    public int getU_id () {
        return u_id;
    }

    public void setU_id ( int u_id ) {
        this.u_id = u_id;
    }

    public String getStartDate () {
        return startDate;
    }

    public void setStartDate ( String startDate ) {
        this.startDate = startDate;
    }

    public String getEndDate () {
        return endDate;
    }

    public void setEndDate ( String endDate ) {
        this.endDate = endDate;
    }

    public int getTotal () {
        return total;
    }

    public void setTotal ( int total ) {
        this.total = total;
    }

    public int getQty () {
        return qty;
    }

    public void setQty ( int qty ) {
        this.qty = qty;
    }

    public String getPaymentId () {
        return paymentId;
    }

    public void setPaymentId ( String paymentId ) {
        this.paymentId = paymentId;
    }

    public boolean isExpanded () {
        return expanded;
    }

    public void setExpanded ( boolean expanded ) {
        this.expanded = expanded;
    }

    @Override
    public String toString () {
        return "HistoryData{" +
                "id=" + id +
                ", u_id=" + u_id +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", total=" + total +
                ", qty=" + qty +
                ", paymentId='" + paymentId + '\'' +
                ", expanded=" + expanded +
                '}';
    }
}
