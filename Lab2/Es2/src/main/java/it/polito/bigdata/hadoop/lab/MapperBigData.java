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
class MapperBigData extends Mapper<
                    LongWritable, // Input key type
                    Text,         // Input value type
                    Text,         // Output key type
                    IntWritable> {// Output value type
    
    protected void map(
            LongWritable key,   // Input key type
            Text value,         // Input value type
            Context context) throws IOException, InterruptedException {
                // Transform word case
                String[] pair = value.toString().split("\\s+");
                int value_word = Integer.parseInt(pair[1]);
                if (value_word < 100) {
                    context.write(new Text("[0, 100)"), new IntWritable(1));
                    }
                else if (value_word >= 100 && value_word > 200){
                    context.write(new Text("[100, 200)"), new IntWritable(1));
                }
                else if (value_word >= 200 && value_word > 300){
                    context.write(new Text("[200, 300)"), new IntWritable(1));
                }
                else if (value_word >= 300 && value_word > 400){
                    context.write(new Text("[300, 400)"), new IntWritable(1));
                }
                else if (value_word >= 400 && value_word > 500){
                    context.write(new Text("[400, 500)"), new IntWritable(1));
                }
                else{
                    context.write(new Text("[500, +inf)"), new IntWritable(1));
                }
            }
    }
