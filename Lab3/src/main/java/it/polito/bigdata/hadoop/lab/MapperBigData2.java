package it.polito.bigdata.hadoop.lab;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Lab  - Mapper
 */

/* Set the proper data types for the (key,value) pairs */
class MapperBigData2 extends Mapper<
                    LongWritable, // Input key type
                    Text,         // Input value type
                    Text,         // Output key type
                    IntWritable> {// Output value type

    TopKVector<WordCountWritable> t = new TopKVector<WordCountWritable>(100);
    
    protected void map(
            LongWritable key,   // Input key type
            Text value,         // Input value type
            Context context) throws IOException, InterruptedException {

            String[] pair_string = value.toString().split("\\s+");
            WordCountWritable pair = new WordCountWritable(pair_string[0], Integer.parseInt(pair_string[1]));
            t.updateWithNewElement(pair);
        }

    protected void cleanup(Context context) throws IOException, InterruptedException {
        // Scrivi i top K locali nel contesto per il Reducer
        for (WordCountWritable wordCount : t.getLocalTopK()) {
            context.write(new Text(wordCount.getWord()), new IntWritable(wordCount.getCount()));
        }
    }
}
