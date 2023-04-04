import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:intl/intl.dart';

class SunsetSunrise extends StatefulWidget {
  const SunsetSunrise({
    super.key,
    required this.title,
    required this.time,
    required this.image,
  });
  final String title;
  final String time;
  final String image;

  @override
  State<SunsetSunrise> createState() => _SunsetSunriseState();
}

class _SunsetSunriseState extends State<SunsetSunrise> {
  final locale = Get.locale;
  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Expanded(
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              Text(
                widget.title,
                style: context.theme.textTheme.titleSmall,
                overflow: TextOverflow.ellipsis,
              ),
              const SizedBox(height: 2),
              Text(
                DateFormat('HH:mm', '${locale?.languageCode}')
                    .format(DateTime.tryParse(widget.time)!),
                style: context.theme.textTheme.titleLarge,
              ),
            ],
          ),
        ),
        Image.asset(
          widget.image,
          scale: 10,
        ),
      ],
    );
  }
}