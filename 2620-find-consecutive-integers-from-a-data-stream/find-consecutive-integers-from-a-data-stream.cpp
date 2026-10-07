class DataStream {
private:
    int val;
    int k_val;
    int count;

public:
    DataStream(int value, int k) {
        val = value;
        k_val = k;
        count = 0;
    }
    
    bool consec(int num) {
        if (num == val) {
            count++;
        } else {
            count = 0;
        }
        
        return count >= k_val;
    }
};