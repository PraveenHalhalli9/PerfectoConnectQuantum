import requests
import json
import sys
import urllib.parse

def PerfectoReportCreation():

    TABLE = '''
    <!DOCTYPE html>
    <html>
    <head>
    <style>

      .option2 { 
        display: inline-block; /* show on the same line */ 
        padding-right: 25px; /* small gap on the right of each header */ 
        text-align:right;
      } 

    table, th, td {
      border: 2px solid black;
      border-collapse: collapse;
    }

    table.center {
      margin-left: auto; 
      margin-right: auto;
    }

    th, td {
      padding: 5px;
    }
    th {
      text-align: center;
    }

    img {
      display: block;
      margin-left: auto;
      margin-right: auto;
    }

    </style>
    <!-- <body style="background-color:powderblue;"> -->


    </head>

        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
          google.charts.load('current', {'packages':['corechart']});
          google.charts.setOnLoadCallback(drawChart);

          function drawChart() {

            var data = google.visualization.arrayToDataTable([
              ['Task', 'Hours per Day'],
              ['Passed',     %s],
              ['Failed',      %s],
              ['UnKnowns',  %s],
            ]);

            var options = {
              title: 'Execution Trends'
            };

            var chart = new google.visualization.PieChart(document.getElementById('piechart'));

            chart.draw(data, options);
          }
        </script>

        <title>Pie Chart</title>
      
        <head>
        </head>

        <img src='https://cdn.brandfolder.io/UEOJKODA/at/ntbjr5wtnmb66b7pkj8rw23/logo-perfecto.auto?height=57&width=300' alt="Perfecto" class="center">

        <body>

            <div id="piechart" style="width: 900px; height: 300px;"></div>

            <h2 class="option2"  >%s \nTESTS</h2>
            <h2 class="option2" style="background-color:green;" >%s \nPASSED</h2>
            <h2 class="option2" style="background-color:tomato;">%s \nFAILED</h2>
            <h2 class="option2" style="background-color:grey;">%s \nUNKNOWNS</h2>



            <table class="center">
                <tr>
                    <th></th>
                    <th></th>
                    <th colspan="3">Platform</th>
                </tr>
                
                <tr>
                    <th>ReportName</th>
                    <th>Status</th>
                    <th>DeviceModel</th>
                    <th>Browser</th>
                    <th>OS</th>
                </tr>
    %s
            </table>
        </body>
    </html>
    '''

    if(len(sys.argv) == 5):
        url = "https://" + sys.argv[1] + ".app.perfectomobile.com/export/api/v1/test-executions?jobName[0]=" + urllib.parse.quote_plus(sys.argv[2]) + "&jobNumber[0]=" + sys.argv[3]

        #print(url)

        header = {'Perfecto-Authorization' : sys.argv[4]}

        r = requests.get(url, headers=header)

        print(r.content)

        res = json.loads(r.content)

        ROW = ' ' * 16 + '<tr> \
                            <td><a href=%s style="display:block;">%s</a></td> \
                            <td>%s</td> \
                            <td>%s</td> \
                            <td>%s</td> \
                            <td>%s</td> \
                          </tr>'
        passed=0
        failed=0
        unknown=0

        rows_list = [ROW % (q['reportURL'], \
                            q['name'],\
                            q['status'],\
                            q['platforms'][0]['mobileInfo']['manufacturer'] + " " + q['platforms'][0]['mobileInfo']['model'] if q['platforms'][0].__contains__('mobileInfo') else q['platforms'][0]['os'], \
                            " " if q['platforms'][0].__contains__('mobileInfo') else q['platforms'][0]['browserInfo']['browserType'] + " " + q['platforms'][0]['browserInfo']['browserVersion'], \
                            q['platforms'][0]['os'] + " " + q['platforms'][0]['osVersion']) for q in res['resources']]

       
        for each in res['resources']:
            #print("st=  " + each['status'])
            if(each['status'] == 'PASSED'):
                passed += 1
            elif(each['status'] == 'FAILED'):
                failed += 1
            elif(each['status'] == 'UNKNOWN'):
                unknown += 1

        strTable = TABLE % (passed, failed, unknown, (len(res['resources'])), passed, failed, unknown, '\n'.join(rows_list))
         
        with open(r"PerfectoReport.html", 'w') as f:
            f.write(strTable)
        #print(strTable)
    else:
        print("Wrong Arguments: Usage <<FileName.py>>, <<CloudName>>, <<JOBName>>, <<JOBNumber>>, <<SecurityToken>>")

if __name__ == "__main__":
    prog = PerfectoReportCreation()

