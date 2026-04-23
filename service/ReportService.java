package service;

public interface ReportService {
    void salesReport();
    void stockReport();
    void lowStockReport(int threshold);
}

