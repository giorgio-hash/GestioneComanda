import python.gen_csv_severity_from_xml as gen_csv_severity_from_xml
import python.gen_piechart_from_csv as gen_piechart_from_csv
import os

# CONFIGURATION
# Get the directory of the script main.py
script_dir = os.path.dirname(os.path.abspath(__file__))
# Build the complete path of the XML file from the script directory
input_xml_file = os.path.join(script_dir, "target", "checkstyle-result.xml")
# name of the output files
csv_rule_file = 'checkstyle_error_counts.csv'
csv_error_file = 'checkstyle_error_severity_counts.csv'
csv_warning_file = 'checkstyle_warning_severity_counts.csv'
csv_info_file = 'checkstyle_info_severity_counts.csv'
image_rule_file = 'error_distribution_pie_chart.png'
image_error_file = 'error_severity_distribution_pie_chart.png'
image_warning_file = 'warning_severity_distribution_pie_chart.png'
image_info_file = 'info_severity_distribution_pie_chart.png'
# SCRIPT
# generate a single global csv
e_counts = gen_csv_severity_from_xml.parse_checkstyle_report(input_xml_file, "", True)
gen_csv_severity_from_xml.write_error_counts_to_csv(e_counts, csv_rule_file, 'Rule')
# generate three csv files with different level of severity
errors_counts = gen_csv_severity_from_xml.parse_checkstyle_report(input_xml_file, "error")
warning_counts = gen_csv_severity_from_xml.parse_checkstyle_report(input_xml_file, "warning")
info_counts = gen_csv_severity_from_xml.parse_checkstyle_report(input_xml_file, "info")
gen_csv_severity_from_xml.write_error_counts_to_csv(errors_counts, csv_error_file, 'Error')
gen_csv_severity_from_xml.write_error_counts_to_csv(warning_counts, csv_warning_file, 'Warning')
gen_csv_severity_from_xml.write_error_counts_to_csv(info_counts, csv_info_file, 'Info')
# generate a pie chart for the error distribution from the global csv
title, error_counts = gen_piechart_from_csv.read_error_counts_from_csv(csv_rule_file)
gen_piechart_from_csv.plot_pie_chart(error_counts, image_rule_file, title)
# generate a pie chart for the error distribution from the global csv
title, error_counts = gen_piechart_from_csv.read_error_counts_from_csv(csv_error_file)
gen_piechart_from_csv.plot_pie_chart(error_counts, image_error_file, title)
# generate a pie chart for the warning distribution from the global csv
title, error_counts = gen_piechart_from_csv.read_error_counts_from_csv(csv_warning_file)
gen_piechart_from_csv.plot_pie_chart(error_counts, image_warning_file, title)
# generate a pie chart for the info distribution from the global csv
title, error_counts = gen_piechart_from_csv.read_error_counts_from_csv(csv_info_file)
gen_piechart_from_csv.plot_pie_chart(error_counts, image_info_file, title)
